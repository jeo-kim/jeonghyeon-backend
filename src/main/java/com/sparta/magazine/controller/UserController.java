package com.sparta.magazine.controller;

import com.sparta.magazine.dto.SignupRequestDto;
import com.sparta.magazine.service.UserService;
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

    // 로그인 실패 메시지
    @GetMapping("/user/login/error")
    public void loginFail() {
        throw new IllegalArgumentException("아이디 또는 패스워드를 확인해주세요.");
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public void registerUser(SignupRequestDto requestDto, HttpServletResponse response) throws IOException {

        String password = requestDto.getPassword();
        String nickname = requestDto.getNickname();
        //닉네임 예외처리
        if (!nickname.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*")) {
            throw new IllegalArgumentException("닉네임에 특수문자는 포함할 수 없습니다.");
        }
        if (nickname.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
            throw new IllegalArgumentException("닉네임에 한글은 포함할 수 없습니다.");
        }
        if (nickname.length() < 3) {
            throw new IllegalArgumentException("닉네임은 최소 3자 이상이어야 합니다.");
        }
        //비밀번호 예외처리
        if (password.contains(nickname)) {
            throw new IllegalArgumentException("비밀번호에 닉네임이 포함될 수 없습니다.");
        }
        userService.registerUser(requestDto);
//        return "redirect:/user/login";
        response.sendRedirect("/user/login");

    }

}
