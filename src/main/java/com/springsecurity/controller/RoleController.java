package com.springsecurity.controller;

import com.springsecurity.dto.request.RoleRequestDto;
import com.springsecurity.dto.response.ApiResponseDto;
import com.springsecurity.dto.response.RoleResponseDto;
import com.springsecurity.service.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleServiceImpl roleService;

    @PostMapping
    ApiResponseDto<RoleResponseDto> create(@RequestBody RoleRequestDto roleRequestDto) {
        return ApiResponseDto.<RoleResponseDto>builder()
                .result(roleService.createRole(roleRequestDto))
                .build();
    }

    @GetMapping
    ApiResponseDto<List<RoleResponseDto>> getAll() {
        return ApiResponseDto.<List<RoleResponseDto>>builder()
                .result(roleService.getAllRoles())
                .build();
    }

    @DeleteMapping("/{roleName}")
    ApiResponseDto<Void> delete(@PathVariable String roleName) {
        roleService.deleteRole(roleName);
        return ApiResponseDto.<Void>builder().build();
    }
}
