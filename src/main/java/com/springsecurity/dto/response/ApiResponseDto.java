package com.springsecurity.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ApiResponseDto<T> {
    private String code = "200";

    private String message;

    private T result;


}
