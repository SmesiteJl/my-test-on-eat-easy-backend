package com.technokratos;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class Service {
  private final UserClient userClient;

  public UserDto createUser(UserDto user) {
    return userClient.createUser(user);
  }

  public List<UserDto> getAllUsers() {
    return userClient.getAllUsers();
  }
}
