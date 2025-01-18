package ru.mai.lessons.rpks.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilterImpl extends OncePerRequestFilter {

  private static final String TOKEN_HEADER = "Authorization";

  //TODO inject JwtVerifierService...

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {

    //TODO - if requestUri startsWith, then skip...

    //TODO extract token...

    //TODO verify token...

    //TODO if not verify, then set status response SC_UNAUTHORIZED...
  }

  private String getToken(HttpServletRequest request) {
    return request.getHeader(TOKEN_HEADER);
  }
}
