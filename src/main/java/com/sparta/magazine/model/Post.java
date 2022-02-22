package com.sparta.magazine.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.magazine.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
@Table(name = "POSTS")
public class Post extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.

    protected Post() {}

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LayoutType layoutType;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Like> likes = new ArrayList<>();

    public Post(User user, PostRequestDto requestDto) {
        this.user = user;
        this.contents = requestDto.getContents();
        this.imageUrl = requestDto.getImageUrl();
        this.layoutType = requestDto.getLayoutType();
    }

    public void update(User user, PostRequestDto requestDto) {
        this.user = user;
        this.contents = requestDto.getContents();
        this.imageUrl = requestDto.getImageUrl();
        this.layoutType = requestDto.getLayoutType();
    }
}