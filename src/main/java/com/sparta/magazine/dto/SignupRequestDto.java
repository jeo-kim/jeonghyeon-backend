package com.sparta.magazine.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class SignupRequestDto {
    private final String userEmail;
    private final String password;
    private final String nickname;
}