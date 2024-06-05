package com.springsecurity.mapper;

import com.springsecurity.dto.request.UserCreationDto;
import com.springsecurity.dto.request.UserUpdatingDto;
import com.springsecurity.dto.response.UserResponseDto;
import com.springsecurity.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationDto userCreationDto);

    UserResponseDto toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdatingDto userUpdatingDto);

}
