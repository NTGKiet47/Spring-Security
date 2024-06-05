package com.springsecurity.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PermissionRequestDto {
    private String permissionName;

    private String description;
}
