package com.sparta.magazine.controller;

import com.sparta.magazine.dto.SignupRequestDto;
import com.sparta.magazine.security.UserDetailsImpl;
import com.sparta.magazine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {

    private final UserService userService;

    @Autowired
    public ViewController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        //TODO 적절한 방식으로 로그인한 사용자 예외처리 메세지 보내기
        if (userDetails != null) {
//            return "이미 로그인이 되어 있습니다.";
            throw new IllegalArgumentException("이미 로그인이 되어 있습니다.");
        }
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
//            return "이미 로그인이 되어 있습니다.";
            throw new IllegalArgumentException("이미 로그인이 되어 있습니다.");
        }
        return "signup";
    }

//    // 회원 가입 요청 처리
//    @PostMapping("/user/signup")
//    public String registerUser(SignupRequestDto requestDto) {
//        userService.registerUser(requestDto);
//        return "redirect:/user/login";
//    }

}