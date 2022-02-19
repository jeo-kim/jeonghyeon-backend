package com.sparta.magazine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    // 로그인 실패 메시지
    @GetMapping("/user/login/error")
    public String loginFail() {
        return "아이디 또는 패스워드를 확인해주세요.";
    }

}
