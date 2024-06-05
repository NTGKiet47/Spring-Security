package com.springsecurity.dto.request;

import com.springsecurity.exception.ErrorCode;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreationDto {

    @Size(min = 3, message = "USERNAME_INVALID")
    private String userName;

    @Size(min = 8, message = "PASSWORD_INVALID")
    private String passWord;

    private String firstName;
    private String lastName;
    private String dob;
}
