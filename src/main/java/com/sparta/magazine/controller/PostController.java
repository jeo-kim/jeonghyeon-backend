package com.sparta.magazine.controller;

import com.sparta.magazine.dto.PostRequestDto;
import com.sparta.magazine.model.Post;
import com.sparta.magazine.model.PostToFE;
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
            String nickname = user.getNickname();
            Post post = postService.createPost(userId, nickname, postRequestDto);
            return post;
        }
    }

    @GetMapping("/api/post")
    public List<PostToFE> getAllPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인했으면 해당 id를, 로그인 안했으면 -1을 userId에 할당
        Long userId = (userDetails !=null) ? userDetails.getUser().getId() : -1;
//        List<Post> posts = postService.readPost();
        List<PostToFE> postsToFE = postService.getAllPosts(userId);
        return postsToFE;
    }

    // TODO post => PostToFE 로 변환해서 보내기
    @GetMapping("api/post/{postId}")
    public PostToFE getSinglePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = (userDetails !=null) ? userDetails.getUser().getId() : -1;

        PostToFE postToFE = postService.getSinglePost(postId, userId);

        return postToFE;
//        return postRepository.findById(id);
    }

    @PutMapping("api/post/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (userDetails == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        Long userId = userDetails.getUser().getId();
        String nickname = userDetails.getUser().getNickname();
        postService.update(postId, userId, nickname, requestDto);
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
