package com.springsecurity.controller;

import com.springsecurity.dto.request.PermissionRequestDto;
import com.springsecurity.dto.response.ApiResponseDto;
import com.springsecurity.dto.response.PermissionResponseDto;
import com.springsecurity.mapper.PermissionMapper;
import com.springsecurity.service.PermissionServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("permission")
@RequiredArgsConstructor
@Slf4j
public class PermissionController {

    private final PermissionServiceImpl permissionService;

    private final PermissionMapper permissionMapper;

    @PostMapping
    public ResponseEntity<ApiResponseDto<PermissionResponseDto>> createPermission(
            @RequestBody PermissionRequestDto permissionRequestDto
    ){
        log.info("----------------------");
        return ResponseEntity.ok(
                ApiResponseDto.<PermissionResponseDto>builder()
                        .message("Permission created successfully")
                        .result(permissionService.createPermission(permissionRequestDto))
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<PermissionResponseDto>>> getAllPermissions(){
        return ResponseEntity.ok(
                ApiResponseDto.<List<PermissionResponseDto>>builder()
                        .message("Get all permissions successfully")
                        .result(permissionService.getAllPermissions())
                        .build()
        );
    }

    @DeleteMapping("/{permissionName}")
    public ResponseEntity<ApiResponseDto<Void>> deletePermission(@PathVariable String permissionName){
        permissionService.deletePermission(permissionName);
        return ResponseEntity.ok(
                ApiResponseDto.<Void>builder()
                        .message("Delete permission successfully")
                        .build()
        );
    }

}
