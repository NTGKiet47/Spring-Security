package com.springsecurity.service;

import com.springsecurity.dto.request.PermissionRequestDto;
import com.springsecurity.dto.response.PermissionResponseDto;
import com.springsecurity.entity.Permission;
import com.springsecurity.mapper.PermissionMapper;
import com.springsecurity.repository.PermissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    private final PermissionMapper permissionMapper;

    @Override
    public PermissionResponseDto createPermission(PermissionRequestDto permissionRequestDto) {

        Permission permission = permissionRepository.save(permissionMapper.toPermission(permissionRequestDto));

        return permissionMapper.toPermissionResponseDto(permission);
    }

    @Override
    public List<PermissionResponseDto> getAllPermissions() {
        return permissionRepository.findAll().stream().map(
                permissionMapper::toPermissionResponseDto
        ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletePermission(String permissionName) {
        permissionRepository.deleteByPermissionName(permissionName);
    }

}
