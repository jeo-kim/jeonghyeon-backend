package com.sparta.magazine.controller;

import com.sparta.magazine.dto.PostRequestDto;
import com.sparta.magazine.dto.PostToFE;
import com.sparta.magazine.model.User;
import com.sparta.magazine.security.UserDetailsImpl;
import com.sparta.magazine.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("/api/post")
    public Long createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            throw new IllegalArgumentException("로그인하지 않은 사용자는 포스팅할 수 없습니다.");
        }
        areValidInputs(requestDto);
        User user = userDetails.getUser();
        Long savedId = postService.createPost(user, requestDto);
        return savedId;
        
    }

    @GetMapping("/api/post")
    public List<PostToFE> getAllPosts(@AuthenticationPrincipal UserDetailsImpl userDetails, Pageable pageable) {
        // 로그인했으면 해당 id를, 로그인 안했으면 -1을 userId에 할당
        Long userId = (userDetails !=null) ? userDetails.getUser().getId() : -1;
        List<PostToFE> postsToFE = postService.getAllPosts(userId, pageable);
        return postsToFE;
    }

    @GetMapping("api/post/{postId}")
    public PostToFE getSinglePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = (userDetails !=null) ? userDetails.getUser().getId() : -1;
        PostToFE postToFE = postService.getSinglePost(postId, userId);
        return postToFE;
    }

    @PutMapping("api/post/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        areValidInputs(requestDto);
        User user = userDetails.getUser();
        postService.update(postId, user, requestDto);
        return postId;
    };

    @DeleteMapping("/api/post/{postId}")
    public String deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        User user = userDetails.getUser();
        String deletedImageUrl = postService.deletePost(postId, user);
        return deletedImageUrl;
    }

    private void areValidInputs(PostRequestDto postRequestDto) {
        String contents = postRequestDto.getContents().trim();
        String imageUrl = postRequestDto.getImageUrl().trim();
        if (contents.length() == 0 || imageUrl.length() == 0) {
            throw new IllegalArgumentException("컨텐츠와 이미지url을 모두 입력해주세요.");
        }
    }
}
