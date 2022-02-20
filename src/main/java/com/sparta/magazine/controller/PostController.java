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
    public Post createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (userDetails == null) {
            throw new IllegalArgumentException("로그인하지 않은 사용자는 포스팅할 수 없습니다.");
        } else {
            User user = userDetails.getUser();
            Long userId = user.getId();
            Post post = postService.createPost(userId, postRequestDto);
            return post;
        }
    }

    //TODO Get에 대해서도 Service의 작업이 필요. 로그인정보 확인해서, 좋아요 여부 반영한 게시글 정보 보내야 하니까.
    @GetMapping("/api/post")
    public List<Post> readPost() {
        List<Post> posts = postService.readPost();
        return posts;
    }

    @GetMapping("api/post/{id}")
    public Optional<Post> singlePost(@PathVariable Long id) {
        return postRepository.findById(id);
    }

    @PutMapping("api/post/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (userDetails == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        Long userId = userDetails.getUser().getId();
        postService.update(postId, userId, requestDto);
        return postId;
    };

    @DeleteMapping("/api/post/{postId}")
    public Long deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (userDetails == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        Long userId = userDetails.getUser().getId();
        Long deletedId = postService.deletePost(postId, userId);

        return deletedId;
    }
}
