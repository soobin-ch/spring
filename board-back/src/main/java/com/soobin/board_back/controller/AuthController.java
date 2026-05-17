package com.soobin.board_back.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soobin.board_back.dto.request.auth.SignInRequestDto;
import com.soobin.board_back.dto.request.auth.SignUpRequestDto;
import com.soobin.board_back.dto.response.auth.SignUpResponseDto;
import com.soobin.board_back.dto.response.auth.signInResponseDto;
import com.soobin.board_back.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    


    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signup(@RequestBody @Valid SignUpRequestDto requestbody) {


        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestbody);
        return response;
    }
    @PostMapping("/sign-in")
    public ResponseEntity<? super signInResponseDto> signIn(@RequestBody @Valid SignInRequestDto requestbody) {
        //TODO: process POST request
        ResponseEntity<? super signInResponseDto> response = authService.signIn(requestbody);
        return response;


    }
    
}
