package com.soobin.board_back.exception;

import org.springframework.http.HttpMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.MethodArgumentBuilder;

import com.soobin.board_back.dto.response.ResponseDto;

@RestController
public class BadRequestExceptionHandler {
    

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ResponseDto> validationFailed(Exception exception) {

    
        return ResponseDto.validationFailed();
    }




}