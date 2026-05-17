package com.soobin.board_back.service.implement;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.soobin.board_back.dto.request.auth.SignInRequestDto;
import com.soobin.board_back.dto.request.auth.SignUpRequestDto;
import com.soobin.board_back.dto.response.ResponseDto;
import com.soobin.board_back.dto.response.auth.SignUpResponseDto;
import com.soobin.board_back.dto.response.auth.signInResponseDto;
import com.soobin.board_back.entity.UserEntity;
import com.soobin.board_back.provider.JwtProvider;
import com.soobin.board_back.repository.UserRepository;
import com.soobin.board_back.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;


    private PasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
      

        try {

            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByEmail(email);
            if(existedEmail) return SignUpResponseDto.duplicateEmail();

            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if(existedNickname) return SignUpResponseDto.duplicateNickname();


            String telNumber = dto.getTelNumber();
            boolean existedTelNumber = userRepository.existsByTelNumber(telNumber);
            if(existedTelNumber) return SignUpResponseDto.duplicatTelNumber();


            String password = dto.getPassword();

            String encodedPassword = PasswordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);

            userRepository.save(userEntity);
            
        }catch(Exception exception)
         {

            exception.printStackTrace();
            return ResponseDto.databaseError();
         }
         return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super signInResponseDto> signIn(SignInRequestDto dto) {


        String token = null;


        try {

            String email = dto.getEmail();
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return signInResponseDto.signInFail();

            String password = dto.getPassword();

            String encodePassword = userEntity.getPassword();

            boolean isMatched = PasswordEncoder.matches(password, encodePassword);

            if(!isMatched) return signInResponseDto.signInFail();


            token = jwtProvider.create(email);

        }catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();

        }
        return signInResponseDto.success((token));
       
    }
    
}
