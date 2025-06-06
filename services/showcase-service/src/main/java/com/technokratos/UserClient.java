package com.technokratos;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
    name = "user-service", // имя сервиса в Consul
    path = "/api/v1/users") // базовый путь как в API
public interface UserClient {
  @PostMapping
  UserDto createUser(@RequestBody UserDto userDto);

  @GetMapping
  List<UserDto> getAllUsers();

  // для примера сохраним пользователя и получим список всех пользователей
}
