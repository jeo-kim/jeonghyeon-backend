package com.sparta.magazine.service;

import com.sparta.magazine.dto.PostRequestDto;
import com.sparta.magazine.model.Post;
import com.sparta.magazine.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post createPost(Long userId, String contents) {
        PostRequestDto postRequestDto = new PostRequestDto(userId, contents);
        Post post = new Post(postRequestDto);
        return postRepository.save(post);
    }

    public List<Post> readPost() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();

        //TODO 프론트에서 보여줄 양식대로 재편성하는 작업 필요할 듯(닉네임, 유저 프로필 이미지, 좋아요 수 등)
        for (Post post : posts) {
            Long likeCount = Long.valueOf(post.getLikes().size());
            System.out.println(post.getId() + "번 게시물의 likeCount = " + likeCount);
        }
        return posts;

    }


    @Transactional
    public Long update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("memo id가 존재하지 않습니다.")
        );
        post.update(requestDto);
        return post.getId();
    }


}
