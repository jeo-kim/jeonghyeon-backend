package com.sparta.magazine.controller;

import com.sparta.magazine.dto.SignupRequestDto;
import com.sparta.magazine.model.User;
import com.sparta.magazine.security.UserDetailsImpl;
import com.sparta.magazine.service.UserService;
import com.sparta.magazine.validator.SignupInputValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@SessionAttributes("user")
public class UserController {

    private final UserService userService;
    private final SignupInputValidator signupInputValidator;


    @PostMapping("/user/login")
    public void login(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        HttpSession session = request.getSession();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        System.out.println("====================== authorities = " + authorities);

    }

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
