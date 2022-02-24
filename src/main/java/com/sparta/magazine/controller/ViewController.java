//package com.sparta.magazine.controller;
//
//import com.sparta.magazine.dto.SignupRequestDto;
//import com.sparta.magazine.security.UserDetailsImpl;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@Controller
//public class ViewController {
//
////    // 회원 로그인 페이지
////    @GetMapping("/user/login")
////    public String login(@AuthenticationPrincipal UserDetailsImpl userDetails) {
////        if (userDetails != null) {
////            throw new IllegalArgumentException("이미 로그인이 되어 있습니다.");
////        }
////        return "login";
////    }
//
////    // 회원 가입 페이지
////    @GetMapping("/user/signup")
////    public String signup(@AuthenticationPrincipal UserDetailsImpl userDetails) {
////        if (userDetails != null) {
////            throw new IllegalArgumentException("이미 로그인이 되어 있습니다.");
////        }
////        return "signup";
////    }
//
//
//    // 회원 로그인 페이지
//    @GetMapping("/user/loginView")
//    public String login() {
//        return "login";
//    }
//
//    // 회원 가입 페이지
//    @GetMapping("/user/signup")
//    public String signup() {
//        return "signup";
//    }
//
////    // 회원 가입 요청 처리
////    @PostMapping("/user/signup")
////    public String registerUser(SignupRequestDto requestDto) {
////        userService.registerUser(requestDto);
////        return "redirect:/user/loginView";
////    }
//}