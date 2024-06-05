package com.springsecurity.controller;

import com.nimbusds.jose.JOSEException;
import com.springsecurity.dto.request.AuthRequestDto;
import com.springsecurity.dto.request.IntrospectTokenRequestDto;
import com.springsecurity.dto.response.ApiResponseDto;
import com.springsecurity.dto.response.AuthResponseDto;
import com.springsecurity.dto.response.IntrospectTokenResponseDto;
import com.springsecurity.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<AuthResponseDto>> authenticate(@RequestBody AuthRequestDto authRequestDto) {
        AuthResponseDto res = authService.authenticate(authRequestDto);

        return ResponseEntity.ok(ApiResponseDto.<AuthResponseDto>builder()
                .message("Login successfully")
                .result(res)
                .build()
        );
    }

    @PostMapping("/introspect")
    public ResponseEntity<ApiResponseDto<IntrospectTokenResponseDto>> authenticate(@RequestBody IntrospectTokenRequestDto introspectTokenDto){
        try {
            IntrospectTokenResponseDto introspectTokenResponseDto = authService.introspectToken(introspectTokenDto);
            return ResponseEntity.ok(
                    ApiResponseDto.<IntrospectTokenResponseDto>builder()
                            .message("Verify token successfully")
                            .result(introspectTokenResponseDto)
                            .build()
            );
        } catch (JOSEException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
