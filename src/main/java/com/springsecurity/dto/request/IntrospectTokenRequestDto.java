package com.springsecurity.dto.request;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class IntrospectTokenRequestDto {
    private String token;
}
