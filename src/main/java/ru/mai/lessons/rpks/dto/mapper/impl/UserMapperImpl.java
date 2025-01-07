package ru.mai.lessons.rpks.dto.mapper.impl;

import org.springframework.stereotype.Service;
import ru.mai.lessons.rpks.dto.mapper.UserMapper;
import ru.mai.lessons.rpks.dto.request.UserRequest;
import ru.mai.lessons.rpks.dto.response.UserResponse;
import ru.mai.lessons.rpks.models.User;

@Service
public class UserMapperImpl implements UserMapper {

  @Override
  public User requestToModel(UserRequest request) {
    //TODO code here...
    return new User();
  }

  @Override
  public UserResponse modelToResponse(UserResponse response) {
    //TODO code here...
    return new UserResponse();
  }
}
