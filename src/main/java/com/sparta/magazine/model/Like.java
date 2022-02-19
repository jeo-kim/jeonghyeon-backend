package com.sparta.magazine.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.magazine.dto.LikeRequestDto;
import com.sparta.magazine.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@Table(name = "LIKES")
public class Like extends Timestamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fkey_user_id"))
    private User user;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "fkey_post_id"))
    private Post post;

    public Like(LikeRequestDto requestDto) {
        this.user = requestDto.getUser();
        this.post = requestDto.getPost();
    }

}
