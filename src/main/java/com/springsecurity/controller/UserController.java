package com.springsecurity.controller;


import com.springsecurity.dto.request.UserCreationDto;
import com.springsecurity.dto.request.UserUpdatingDto;
import com.springsecurity.dto.response.ApiResponseDto;
import com.springsecurity.dto.response.UserResponseDto;
import com.springsecurity.entity.User;
import com.springsecurity.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@Slf4j
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<UserResponseDto>> createUser(@RequestBody @Valid UserCreationDto userCreation) {
        ApiResponseDto<UserResponseDto> apiResponseDto = new ApiResponseDto<UserResponseDto>();

        apiResponseDto.setMessage("User created successfully");
        apiResponseDto.setResult(userService.createUser(userCreation));

        return ResponseEntity.ok(apiResponseDto);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<Set<User>>> getAllUsers() {
        ApiResponseDto<Set<User>> apiResponseDto = new ApiResponseDto<>();

        apiResponseDto.setMessage("Get all users");
        apiResponseDto.setResult(userService.getAllUsers());

        return ResponseEntity.ok(apiResponseDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<User>> getUser(@PathVariable Long userId) {
        ApiResponseDto<User> apiResponseDto = new ApiResponseDto<>();

        apiResponseDto.setMessage("Get user with userid: " + userId);
        apiResponseDto.setResult(userService.getUser(userId));

        return ResponseEntity.ok(apiResponseDto);
    }

    @GetMapping("/myInfo")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> getMyInfo(){

        ApiResponseDto<UserResponseDto> apiResponseDto = new ApiResponseDto<>();
        apiResponseDto.setMessage("My information");
        apiResponseDto.setResult(userService.getMyInfo());

        return ResponseEntity.ok(apiResponseDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<User>> updateUser(@PathVariable Long userId, @RequestBody UserUpdatingDto updateUser) {

        ApiResponseDto<User> apiResponseDto = new ApiResponseDto<>();
        User updatedUser = userService.updateUser(userId, updateUser);

        apiResponseDto.setMessage("User updated successfully: ");
        apiResponseDto.setResult(updatedUser);

        return ResponseEntity.ok(apiResponseDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<String>> deleteUser(@PathVariable Long userId) {
        ApiResponseDto<String> apiResponseDto = new ApiResponseDto<>();

        userService.deleteUser(userId);
        apiResponseDto.setMessage("User delete successfully");

        return ResponseEntity.ok(apiResponseDto);
    }
}
