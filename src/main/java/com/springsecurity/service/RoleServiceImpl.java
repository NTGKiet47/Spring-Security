package com.springsecurity.service;

import com.springsecurity.dto.request.RoleRequestDto;
import com.springsecurity.dto.response.RoleResponseDto;
import com.springsecurity.entity.Permission;
import com.springsecurity.entity.Role;
import com.springsecurity.mapper.RoleMapper;
import com.springsecurity.repository.PermissionRepository;
import com.springsecurity.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    private final RoleMapper roleMapper;

    @Override
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) {
        Role role = roleMapper.toRole(roleRequestDto);

        Set<Permission> permissionSet = new LinkedHashSet<>();
        roleRequestDto.getPermissions().forEach(permission ->
                permissionSet.add(
                        permissionRepository.findByPermissionName(permission)
                )
        );

        role.setPermissions(permissionSet);
        roleRepository.save(role);

        return roleMapper.toRoleResponseDto(roleRequestDto);
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteRole(String roleName){
        roleRepository.deleteByRoleName(roleName);
    }
}
