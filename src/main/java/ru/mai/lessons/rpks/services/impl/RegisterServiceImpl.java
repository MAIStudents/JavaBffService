package ru.mai.lessons.rpks.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mai.lessons.rpks.dto.response.TokenResponse;
import ru.mai.lessons.rpks.services.RegisterService;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

  //TODO inject UserService...

  //TODO inject token.signing.secret value...
  @Value("${token.signing.secret}")
  private String secret;

  //TODO inject token.signing.issuer value...
  @Value("${token.issuer}")
  private String issuer;

  @Override
  public TokenResponse register(String username) {
    //TODO generate token by JWT with issuer, subject, issuedAt, expiresAt and sign by HMAC256...
    //TODO create user...
    return new TokenResponse();
  }
}
