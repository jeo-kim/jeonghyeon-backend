package com.sparta.magazine.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long userId;

    private String userEmail;

    private String nickname;

    @Builder
    public UserResponseDto(Long userId, String userEmail, String nickname){
        this.userId = userId;
        this.userEmail = userEmail;
        this.nickname = nickname;
    }
}