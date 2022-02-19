package com.sparta.magazine.controller;

import com.sparta.magazine.dto.PostRequestDto;
import com.sparta.magazine.model.Post;
import com.sparta.magazine.model.User;
import com.sparta.magazine.repository.PostRepository;
import com.sparta.magazine.security.UserDetailsImpl;
import com.sparta.magazine.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    @PostMapping("/api/post")
    public Post createPost(@RequestBody String contents,  @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (userDetails == null) {
            throw new IllegalArgumentException("로그인하지 않은 사용자는 포스팅할 수 없습니다.");
        } else {
            User user = userDetails.getUser();
            Long userId = user.getId();
            Post post = postService.createPost(userId, contents);
            return post;
        }
    }

    @GetMapping("/api/post")
    public List<Post> readPost() {
        List<Post> posts = postService.readPost();
        return posts;
    }

    @GetMapping("api/post/{id}")
    public Optional<Post> singlePost(@PathVariable Long id) {
        return postRepository.findById(id);
    }

    @PutMapping("api/post/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        postService.update(id, requestDto);
        return id;
    };

    @DeleteMapping("/api/post/{id}")
    public Long deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }
}
