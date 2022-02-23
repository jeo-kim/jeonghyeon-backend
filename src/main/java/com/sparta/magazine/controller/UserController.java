package com.sparta.magazine.controller;

import com.sparta.magazine.dto.SignupRequestDto;
import com.sparta.magazine.service.UserService;
import com.sparta.magazine.validator.SignupInputValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final SignupInputValidator signupInputValidator;

    // 로그인 실패 메시지
    @GetMapping("/user/login/error")
    public void loginFail() {
        throw new IllegalArgumentException("아이디 또는 패스워드를 확인해주세요.");
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public void registerUser(SignupRequestDto requestDto, HttpServletResponse response) throws IOException {
        String userEmail = requestDto.getUserEmail().trim();
        String password = requestDto.getPassword().trim();
        String nickname = requestDto.getNickname().trim();

        signupInputValidator.IsValidSignupInput(userEmail, password, nickname);

        userService.registerUser(requestDto);
        response.sendRedirect("/user/login");

    }

}
