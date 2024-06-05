package com.springsecurity.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PermissionResponseDto {
    private String permissionName;

    private String description;
}
