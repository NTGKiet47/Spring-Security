package com.springsecurity.service;

import com.springsecurity.dto.request.RoleRequestDto;
import com.springsecurity.dto.response.RoleResponseDto;

import java.util.List;
import java.util.Set;

public interface RoleService {
    RoleResponseDto createRole(RoleRequestDto roleRequestDto);

    List<RoleResponseDto> getAllRoles();
}
