package com.sparta.magazine.controller;

import com.sparta.magazine.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class ViewController {

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            throw new IllegalArgumentException("이미 로그인이 되어 있습니다.");
        }
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            throw new IllegalArgumentException("이미 로그인이 되어 있습니다.");
        }
        return "signup";
    }

}