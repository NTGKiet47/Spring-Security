package com.springsecurity.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AuthRequestDto {
    private String userName;
    private String passWord;
}
