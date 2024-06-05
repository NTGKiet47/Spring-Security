package com.springsecurity.dto.request;

import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestDto {
    private String name;
    private String description;
    private Set<String> permissions;
}
