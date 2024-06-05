package com.springsecurity.exception;

import com.springsecurity.dto.response.ApiResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    // this method will handle all the exception during runtime that catches manually
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponseDto<String>> handlingAppException(AppException appException){

        ErrorCode errorCode = appException.getErrorCode();

        ApiResponseDto<String> apiResponseDto = new ApiResponseDto<>();
        apiResponseDto.setCode(errorCode.getCode());
        apiResponseDto.setMessage(appException.getMessage());

        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(apiResponseDto);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiResponseDto<String>> handlingAuthorizationException(AuthorizationDeniedException exception){
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(
                        ApiResponseDto.<String>builder()
                                .code(errorCode.getCode())
                                .message(errorCode.getMessage())
                                .build()
                );
    }

    // this method will handle the rest (unexpected exceptions)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<String>> handlingOthersException(RuntimeException exception){
        ApiResponseDto<String> apiResponseDto = new ApiResponseDto<>();

        apiResponseDto.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponseDto.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiResponseDto);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<String>> handlingValidationException(MethodArgumentNotValidException exception){
        ErrorCode errorCode = ErrorCode.valueOf(exception.getFieldError().getDefaultMessage());

        ApiResponseDto<String> apiResponseDto = new ApiResponseDto<>();
        apiResponseDto.setCode(errorCode.getCode());
        apiResponseDto.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponseDto);
    }

}
