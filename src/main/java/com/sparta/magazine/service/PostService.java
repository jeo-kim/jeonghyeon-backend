package com.sparta.magazine.service;

import com.sparta.magazine.dto.PostRequestDto;
import com.sparta.magazine.model.Post;
import com.sparta.magazine.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post createPost(Long userId, PostRequestDto postRequestDto) {
//        PostRequestDto postRequestDto = new PostRequestDto(userId, contents, imageUrl);
        Post post = new Post(userId, postRequestDto);
        return postRepository.save(post);
    }

    public List<Post> readPost() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();

        //TODO 프론트에서 보여줄 양식대로 재편성하는 작업 필요할 듯(닉네임, 좋아요 수, 각 게시물과 로그인한 사용자의 좋아요 관계 등)
        for (Post post : posts) {
            Long likeCount = Long.valueOf(post.getLikes().size());
            System.out.println(post.getId() + "번 게시물의 likeCount = " + likeCount);
        }
        return posts;

    }


    @Transactional
    public Long update(Long postId, Long userId, PostRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 postId가 존재하지 않습니다.")
        );
        Long originUserId = post.getUserId();
        if (!Objects.equals(userId, originUserId)) {
            throw new IllegalArgumentException("작성자가 아니면 수정할 수 없습니다.");
        }

        post.update(userId, requestDto);
        return post.getId();
    }


    public Long deletePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 postId가 존재하지 않습니다.")
        );

        Long originUserId = post.getUserId();
        if (!Objects.equals(userId, originUserId)) {
            throw new IllegalArgumentException("작성자가 아니면 삭제할 수 없습니다.");
        }

        postRepository.deleteById(postId);
        return postId;
    }
}
