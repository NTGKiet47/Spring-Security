package com.springsecurity.mapper;

import com.springsecurity.dto.request.PermissionRequestDto;
import com.springsecurity.dto.response.PermissionResponseDto;
import com.springsecurity.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequestDto permissionRequestDto);

    PermissionResponseDto toPermissionResponseDto(Permission permission);
}
