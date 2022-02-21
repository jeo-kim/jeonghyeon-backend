package com.sparta.magazine.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class SignupRequestDto {
    private String userEmail;
    private String password;
    private String nickname;
}