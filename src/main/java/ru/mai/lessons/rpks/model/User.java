package ru.mai.lessons.rpks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails {

  @Id
  private Long id;

  @Column(name = "username", unique = true, nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  /**
   * Получение ролей пользователя.
   *
   * @return роли пользователя
   */
  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  /**
   * Получение пароля пользователя.
   *
   * @return пароль пользователя
   */
  @Override
  public String getPassword() {
    return password;
  }

  /**
   * Получение логина(имени) пользователя.
   *
   * @return логин(имя) пользователя
   */
  @Override
  public String getUsername() {
    return username;
  }

  /**
   * Не истек ли срок использования аккаунта пользователя.
   *
   * @return значение true - не истек, false - истек
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * Не заблокирован ли аккаунт пользователя.
   *
   * @return значение true - не заблокирован, false - заблокирован
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * Не истек ли срок использования пароля.
   *
   * @return значение true - не истек, false - истек
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * Не отключен ли аккаунт пользователя.
   *
   * @return значение true - не отключен, false - отключен
   */
  @Override
  public boolean isEnabled() {
    return true;
  }
}
