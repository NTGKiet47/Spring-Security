package com.springsecurity.service;

import com.springsecurity.dto.request.UserCreationDto;
import com.springsecurity.dto.request.UserUpdatingDto;
import com.springsecurity.dto.response.UserResponseDto;
import com.springsecurity.entity.User;

import java.util.Set;

public interface UserService {
    UserResponseDto createUser(UserCreationDto user);

    Set<User> getAllUsers();

    User getUser(Long userId);

    User updateUser(Long userId, UserUpdatingDto updatedUser);

    void deleteUser(Long userId);

    UserResponseDto getMyInfo();
}
