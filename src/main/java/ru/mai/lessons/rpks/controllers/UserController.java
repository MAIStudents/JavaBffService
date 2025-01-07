package ru.mai.lessons.rpks.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import ru.mai.lessons.rpks.dto.request.UserRequest;
import ru.mai.lessons.rpks.dto.response.UserResponse;

@Tag(
    name = "Контроллер для регистрации пользователей",
    description = "Контроллер для регистрации пользователей"
)
public interface UserController {

  @Operation(
      summary = "Регистрация пользователя",
      description = "Регистрация пользователя",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешная регистрация пользователя",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = UserResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Неверный формат запроса",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = BadRequest.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Ошибка на стороне сервиса",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = InternalServerError.class)
              )
          )
      }
  )
  @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Запрос на регистрацию пользователя",
      required = true,
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = UserRequest.class)
      )
  )
  UserResponse register(@RequestBody @Valid UserRequest request);
}
