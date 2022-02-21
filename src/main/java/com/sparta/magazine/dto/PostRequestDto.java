package com.sparta.magazine.dto;

import com.sparta.magazine.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PostRequestDto {
//    private final Long userId;
    private final String imageUrl;
    private final String contents;
}
