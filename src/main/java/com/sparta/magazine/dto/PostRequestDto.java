package com.sparta.magazine.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PostRequestDto {
    private final String imageUrl;
    private final String contents;
    //TODO enum 타입으로 하는게 좋을까? (ex. EnumType.STRING) 프론트에서도 일치된 글자로 받아와야 하나?
    private final String layoutType;
}
