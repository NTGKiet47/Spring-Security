package com.springsecurity.mapper;

import com.springsecurity.dto.request.RoleRequestDto;
import com.springsecurity.dto.response.RoleResponseDto;
import com.springsecurity.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequestDto roleRequestDto);

    RoleResponseDto toRoleResponseDto(RoleRequestDto roleRequestDto);

    RoleResponseDto toRoleResponseDto(Role role);

}
