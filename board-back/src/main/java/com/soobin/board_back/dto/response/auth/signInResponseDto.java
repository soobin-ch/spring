package com.soobin.board_back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.soobin.board_back.common.ResponseCode;
import com.soobin.board_back.common.ResponseMessage;
import com.soobin.board_back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class signInResponseDto extends ResponseDto
{
    
    private String token;

    private int expirationTime;


    private signInResponseDto(String token) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.token = token;
        this.expirationTime = 3600;
    }

    public static ResponseEntity<signInResponseDto> success(String token) {



        signInResponseDto result = new signInResponseDto(token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> signInFail() {
        ResponseDto result = new ResponseDto((ResponseCode.SIGN_IN_FAIL), ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }


}
