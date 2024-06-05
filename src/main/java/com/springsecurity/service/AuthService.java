package com.springsecurity.service;

import com.springsecurity.dto.request.AuthRequestDto;
import com.springsecurity.dto.response.AuthResponseDto;

public interface AuthService {
    AuthResponseDto authenticate(AuthRequestDto authRequestDto);
}
