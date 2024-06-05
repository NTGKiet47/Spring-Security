package com.springsecurity.dto.response;

import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDto {
    private String name;
    private String description;
    private Set<String> permissions;
}