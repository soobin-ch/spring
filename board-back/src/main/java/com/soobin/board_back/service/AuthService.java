package com.soobin.board_back.service;

import org.springframework.http.ResponseEntity;

import com.soobin.board_back.dto.request.auth.SignInRequestDto;
import com.soobin.board_back.dto.request.auth.SignUpRequestDto;
import com.soobin.board_back.dto.response.auth.SignUpResponseDto;
import com.soobin.board_back.dto.response.auth.signInResponseDto;

public interface AuthService {
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super signInResponseDto> signIn(SignInRequestDto dto);

}
