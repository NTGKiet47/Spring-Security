package com.springsecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsecurity.dto.response.ApiResponseDto;
import com.springsecurity.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // for example a use has his/her own token when logging in
    // assuming this user is just a regular user, he/she tries to access links allow only administrate authorization
    // then this will cause the error 401 --> need to handle by ENTRYPOINT
    // On the other hand, if the token is INCORRECT, it will cause the error 403 --> can be handled with @controllerAdvice

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;

        response.setStatus(errorCode.getHttpStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponseDto<String> apiResponseDto = ApiResponseDto.<String>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(apiResponseDto));
        response.flushBuffer();
    }
}
