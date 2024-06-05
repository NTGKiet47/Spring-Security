package com.springsecurity.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ErrorCode {
    USER_EXISTED("5001", "User existed", HttpStatus.BAD_REQUEST),
    USER_NOTFOUND("5002", "User not found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED("5003", "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("5504", "You do not have permission", HttpStatus.FORBIDDEN),
    PASSWORD_INVALID("5101","Password must at least 8 character-long", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID("5102", "Username must at least 3 character-long", HttpStatus.BAD_REQUEST),
    FAIL_TO_SEE_MY_INFO("5503", "Please login to see your information", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION("999", "Error uncategorized", HttpStatus.INTERNAL_SERVER_ERROR);
//    USER_NOTFOUND("502", "User not found");

    private String code;
    private String message;
    private HttpStatusCode httpStatusCode;

}
