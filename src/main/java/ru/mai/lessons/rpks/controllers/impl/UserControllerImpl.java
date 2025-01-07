package ru.mai.lessons.rpks.controllers.impl;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.mai.lessons.rpks.controllers.UserController;
import ru.mai.lessons.rpks.dto.request.UserRequest;
import ru.mai.lessons.rpks.dto.response.UserResponse;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

  @Override
  @PostMapping("/register")
  @ResponseStatus(HttpStatus.OK)
  public UserResponse register(@RequestBody @Valid UserRequest request) {
    //TODO code here...
    return new UserResponse();
  }
}
