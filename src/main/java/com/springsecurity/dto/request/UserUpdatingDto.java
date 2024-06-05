package com.springsecurity.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdatingDto {
    @Size(min = 8, message = "Password must at least 8 character-long")
    private String passWord;
    private String firstName;
    private String lastName;
    private String dob;
}
