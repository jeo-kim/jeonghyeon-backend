package com.sparta.magazine.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class PostToFE {
    private final Long postId;
    private final String nickname;
    private final String createdAt;
    private final String contents;
    private final String imageUrl;
    private final Long likeCnt;
    private final Boolean userLiked;
}
