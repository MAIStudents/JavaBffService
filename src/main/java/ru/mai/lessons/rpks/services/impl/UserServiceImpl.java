package ru.mai.lessons.rpks.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mai.lessons.rpks.models.User;
import ru.mai.lessons.rpks.services.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  //TODO inject UserRepository...

  @Override
  public User createUser(User user) {
    //TODO code here...
    return new User();
  }

  //TODO cache here...
  @Override
  public User loadUserByUsername(String username) {
    //TODO code here...
    return new User();
  }
}
