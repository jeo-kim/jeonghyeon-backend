package com.sparta.magazine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@Table(name = "USERS")
public class User {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

//    @OneToMany(mappedBy = "author")
//    private List<Post> posts = new ArrayList<>();


//    //TODO @Column 없어도 되나??
//    @Column(nullable = false)
    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();


    public User(String userEmail, String password, String nickname) {
        this.userEmail = userEmail;
        this.password = password;
        this.nickname = nickname;
    }
}