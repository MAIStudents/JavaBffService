package ru.mai.lessons.rpks.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.mai.lessons.rpks.dto.request.UserRequest;
import ru.mai.lessons.rpks.dto.response.UserResponse;

public interface UserService extends UserDetailsService {

  UserResponse register(UserRequest user);
}
