package com.springsecurity.dto.response;

import com.springsecurity.entity.Role;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Set<Role> roles;
}
