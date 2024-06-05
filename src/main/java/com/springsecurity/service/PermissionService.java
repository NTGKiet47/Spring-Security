package com.springsecurity.service;

import com.springsecurity.dto.request.PermissionRequestDto;
import com.springsecurity.dto.response.PermissionResponseDto;
import com.springsecurity.entity.Permission;

import java.util.List;
import java.util.stream.Collectors;

public interface PermissionService {
    public PermissionResponseDto createPermission(PermissionRequestDto permissionRequestDto);

    public List<PermissionResponseDto> getAllPermissions();

    public void deletePermission(String permissionName);
}
