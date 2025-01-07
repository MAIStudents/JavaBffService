package ru.mai.lessons.rpks.dto.mapper;

import ru.mai.lessons.rpks.dto.request.UserRequest;
import ru.mai.lessons.rpks.dto.response.UserResponse;
import ru.mai.lessons.rpks.models.User;

public interface UserMapper {

  User requestToModel(UserRequest request);

  UserResponse modelToResponse(UserResponse response);
}
