package com.gothaxcity.userservice.service;

import com.gothaxcity.userservice.domain.UserEntity;
import com.gothaxcity.userservice.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(String userId);
    Iterable<UserEntity> getAllUsers();
}
