package com.sparta.magazine.controller;

import com.sparta.magazine.dto.LikeRequestDto;
import com.sparta.magazine.model.Like;
import com.sparta.magazine.model.Post;
import com.sparta.magazine.model.User;
import com.sparta.magazine.repository.LikeRepository;
import com.sparta.magazine.repository.PostRepository;
import com.sparta.magazine.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    @GetMapping("/api/post/{post_id}/like")
    public String createLike(@PathVariable Long post_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // spring security 에 의해 로그인한 user 객체 받아오고
        User user = userDetails.getUser();
        // get으로 받는 post_id로 post 객체도 받아온다.
        Post post = postRepository.getById(post_id);


        //이 user 가 이 post 에 대한 like 한적 있는지 likeRepository 에서 찾아보기
        Optional<Like> optionalLike = likeRepository.findByUserAndPost(user, post);

        if (optionalLike.isPresent()) {
            // 이미 like 존재하던 것이었다면, 해당 like entity 를 삭제하여 좋아요 취소 처리
            Long likeId = optionalLike.get().getId();
            likeRepository.deleteById(likeId);
            return "좋아요 취소완료: " + likeId;
        } else {
            // dto 통해서 like 만들고 repository 에 save.
            LikeRequestDto requestDto = new LikeRequestDto(user, post);
            Like like = new Like(requestDto);
            Like save = likeRepository.save(like);
            return "좋아요 추가완료: " + save.getId();
        }


    }


}
