package com.sparta.magazine.model;

import com.sparta.magazine.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
@Table(name = "POSTS")
public class Post extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String imageUrl;

    @OneToMany(mappedBy = "post")
    private List<Like> likes = new ArrayList<>();

    public Post(Long userId, String nickname, PostRequestDto requestDto) {
//        this.userId = requestDto.getUserId();
        this.userId = userId;
        this.nickname = nickname;
        this.contents = requestDto.getContents();
        this.imageUrl = requestDto.getImageUrl();
    }

    public void update(Long userId, String nickname, PostRequestDto requestDto) {
//        this.userId = requestDto.getUserId();
        this.userId = userId;
        this.nickname = nickname;
        this.contents = requestDto.getContents();
        this.imageUrl = requestDto.getImageUrl();
    }
}