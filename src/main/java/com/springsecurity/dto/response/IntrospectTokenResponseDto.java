package com.springsecurity.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class IntrospectTokenResponseDto {
    private Boolean valid;
}
