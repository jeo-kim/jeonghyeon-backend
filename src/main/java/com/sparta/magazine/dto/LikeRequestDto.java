package com.sparta.magazine.dto;

import com.sparta.magazine.model.Post;
import com.sparta.magazine.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class LikeRequestDto {
    private final User user;
    private final Post post;

}
