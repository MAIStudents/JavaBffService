package ru.mai.lessons.rpks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import ru.mai.lessons.rpks.dto.request.UserRequest;
import ru.mai.lessons.rpks.dto.response.UserResponse;
import ru.mai.lessons.rpks.models.User;
import ru.mai.lessons.rpks.repositories.UserRepository;
import ru.mai.lessons.rpks.services.UserService;

@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {JavaBffServiceTest.Initializer.class})
public class JavaBffServiceTest {

  public static final String DEDUPLICATION_URL = "http://localhost:8081/deduplication";
  public static final String ENRICHMENT_URL = "http://localhost:8081/enrichment";
  public static final String FILTER_URL = "http://localhost:8081/filter";

  private static final Integer REDIS_PORT = 6379;
  private static final Integer POSTGRES_PORT = 5432;
  private static final Integer APPLICATION_PORT = 8080;

  private static final Boolean ENABLED_LIQUIBASE = true;

  @LocalServerPort
  private int port;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private CacheManager cacheManager;

  @BeforeEach
  public void setup() {
    RestAssured.port = port;
    userRepository.deleteAll();
  }

  @Container
  public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(
      DockerImageName.parse("postgres"))
      .withDatabaseName("test_db")
      .withUsername("username")
      .withPassword("password")
      .withReuse(true)
      .withExposedPorts(POSTGRES_PORT)
      .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
          new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(POSTGRES_PORT),
              new ExposedPort(POSTGRES_PORT)))
      ));

  @Container
  public static GenericContainer<?> redisContainer = new GenericContainer<>(
      DockerImageName.parse("redis"))
      .withExposedPorts(6379)
      .waitingFor(Wait.forListeningPort())
      .withReuse(true);

  static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues.of(
          "spring.datasource.url=" + postgresContainer.getJdbcUrl(),
          "spring.datasource.username=" + postgresContainer.getUsername(),
          "spring.datasource.password=" + postgresContainer.getPassword(),
          "spring.liquibase.enabled=" + ENABLED_LIQUIBASE,
          "spring.data.redis.host=" + redisContainer.getHost(),
          "spring.data.redis.port=" + redisContainer.getMappedPort(REDIS_PORT),
          "feign.client.url.deduplication=" + DEDUPLICATION_URL,
          "feign.client.url.enrichment=" + ENRICHMENT_URL,
          "feign.client.url.filter=" + FILTER_URL,
          "server.port=" + APPLICATION_PORT
      ).applyTo(configurableApplicationContext.getEnvironment());
    }
  }

  @Test
  @DisplayName("Тест на корректную миграцию")
  void testTableCreatedAfterMigration() {
    String tableName = "user";
    String sql = "SELECT to_regclass('public." + tableName + "');";

    String result = jdbcTemplate.queryForObject(sql, String.class);

    assertNotNull(result, "Таблица " + tableName + " не была создана после миграции");
  }

  @Test
  @DisplayName("Тест на успешное получение доступа к защищенным ресурсам")
  void givenGoodUser_whenFilterFindAll_thenAccessDenied() {
    userRepository.saveAndFlush(
        User.builder()
            .id(1L)
            .username("Domoroschenov")
            .password(passwordEncoder.encode("Password"))
            .build()
    );
    Response loginResponse = RestAssured.given()
        .contentType(ContentType.URLENC)
        .formParam("username", "Domoroschenov")
        .formParam("password", "Password")
        .when()
        .post("/login")
        .then()
        .log().all()
        .statusCode(302)
        .extract()
        .response();

    RestAssured.given()
        .cookie("JSESSIONID", loginResponse.getCookie("JSESSIONID"))
        .get("/filter/findAll")
        .then()
        .log().all()
        .header("Content-Type", ContentType.JSON.toString());
  }

  @Test
  @DisplayName("Тест на неуспешное получение доступа к защищенным ресурсам")
  void givenWrongUser_whenFilterFindAll_thenAccessDenied() {
    Response loginResponse = RestAssured.given()
        .contentType(ContentType.URLENC)
        .formParam("username", "Domoroschenov")
        .formParam("password", "Password")
        .when()
        .post("/login")
        .then()
        .log().all()
        .statusCode(302)
        .extract()
        .response();

    RestAssured.given()
        .cookie("JSESSIONID", loginResponse.getCookie("JSESSIONID"))
        .get("/filter/findAll")
        .then()
        .log().all()
        .header("Content-Type", ContentType.HTML.toString());
  }

  @Test
  @DisplayName("Тест на регистрацию пользователя")
  void givenUserInfo_whenRegister_thenAddUserInDatabaseAndReturnResponse() {
    UserRequest request = UserRequest
        .builder()
        .id(1L)
        .username("Domoroschenov")
        .password("Password")
        .build();
    UserResponse expectedResponse = UserResponse
        .builder()
        .id(1L)
        .username("Domoroschenov")
        .password("Password")
        .build();

    UserResponse actualResponse = RestAssured.given()
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .post("/user/register")
        .then()
        .log().all()
        .statusCode(200)
        .extract().as(UserResponse.class);

    assertEquals(expectedResponse, actualResponse,
        "Пользователь не смог корректно зарегистрироваться");
    assertNotNull(userRepository.findById(1L).orElse(null),
        "Информация о зарегистрированном пользователе не записалась в базу данных");
  }

  @Test
  @DisplayName("Тест на успешную авторизацию и аутентификацию пользователя")
  void givenUserInDataBase_whenLogin_thenReturnAccessAllowed() {
    userRepository.saveAndFlush(
        User.builder()
            .id(1L)
            .username("Domoroschenov")
            .password(passwordEncoder.encode("Password"))
            .build()
    );

    Response actualResponse = RestAssured.given()
        .contentType(ContentType.URLENC)
        .formParam("username", "Domoroschenov")
        .formParam("password", "Password")
        .when()
        .post("/login")
        .then()
        .log().all()
        .statusCode(302)
        .extract()
        .response();

    assertFalse(actualResponse.getHeader("Location").contains("error"),
        "Пользователь не смог авторизоваться, хотя он должен быть в базе данных");
  }

  @Test
  @DisplayName("Тест на неуспешную авторизацию пользователя")
  void givenUserInDataBase_whenLogin_thenReturnAccessDenied() {
    Response actualResponse = RestAssured.given()
        .contentType(ContentType.URLENC)
        .formParam("username", "Domoroschenov")
        .formParam("password", "123")
        .when()
        .post("/login")
        .then()
        .log().all()
        .statusCode(302)
        .extract()
        .response();

    assertTrue(actualResponse.getHeader("Location").contains("error"),
        "Пользователь смог авторизоваться, хотя его нет в базе данных");
  }

  @Test
  @DisplayName("Тест на корректную работу кэша")
  void givenUsername_whenLoadByUsername_thenReturnValueFromCache() {
    User expectedUserInCache = User
        .builder()
        .id(1L)
        .username("Domoroschenov")
        .password(passwordEncoder.encode("Password"))
        .build();
    userRepository.saveAndFlush(
        User.builder()
            .id(1L)
            .username("Domoroschenov")
            .password(passwordEncoder.encode("Password"))
            .build()
    );
    userService.loadUserByUsername("Domoroschenov");
    Cache redisCache = cacheManager.getCache("UsernameCache");

    User actualValueFromCache = (User) redisCache.get("Domoroschenov").get();

    assertEquals(expectedUserInCache.getId(), actualValueFromCache.getId());
    assertEquals(expectedUserInCache.getUsername(), actualValueFromCache.getUsername());
  }
}