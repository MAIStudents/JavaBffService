package ru.mai.lessons.rpks.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtVerifierService {

  //TODO inject UserService...

  @Value("${token.signing.secret}")
  private String secret;

  @Value("${token.issuer}")
  private String issuer;

  public boolean verify(String token) {
    //TODO if token == null then?...
    //TODO verify...
    //TODO use Algorithm.HMAC256(secret)...
    //TODO use JWT for verify...
    //TODO check user in database...
    return false;
  }
}
