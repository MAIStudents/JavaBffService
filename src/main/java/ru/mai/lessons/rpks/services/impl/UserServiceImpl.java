package ru.mai.lessons.rpks.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mai.lessons.rpks.dto.request.UserRequest;
import ru.mai.lessons.rpks.dto.response.UserResponse;
import ru.mai.lessons.rpks.models.User;
import ru.mai.lessons.rpks.services.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  //TODO use repository...
  //TODO use PasswordEncoder...
  //TODO use UserMapper...

  @Override
  public UserResponse register(UserRequest user) {
    //TODO code here...
    return new UserResponse();
  }

  //TODO cache here...
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //TODO code here...
    return new User();
  }
}
