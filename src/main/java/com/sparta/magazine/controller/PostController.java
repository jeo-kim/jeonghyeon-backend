package com.sparta.magazine.controller;

import com.sparta.magazine.dto.PostRequestDto;
import com.sparta.magazine.model.Post;
import com.sparta.magazine.model.User;
import com.sparta.magazine.repository.PostRepository;
import com.sparta.magazine.security.UserDetailsImpl;
import com.sparta.magazine.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        User user = userDetails.getUser();
        if (user==null) {
            throw new RuntimeException("로그인하지 않은 사용자는 포스팅할 수 없습니다.");
        }
        Long userId = user.getId();

//        String nickname = user.getNickname();
        PostRequestDto postRequestDto = new PostRequestDto(userId, contents);
        Post post = new Post(postRequestDto);
        return postRepository.save(post);
    }

    @GetMapping("/api/post")
    public List<Post> readPost() {
        return postRepository.findAllByOrderByModifiedAtDesc();
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
