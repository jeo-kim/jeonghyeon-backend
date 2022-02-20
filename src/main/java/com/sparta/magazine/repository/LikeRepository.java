package com.sparta.magazine.repository;

import com.sparta.magazine.model.Like;
import com.sparta.magazine.model.Post;
import com.sparta.magazine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
//    List <Optional<Like>> findAllByUserId(String userId);
//    List<Optional<Like>> findAllByPostId(String postId);
    Optional<Like> findByUserAndPost(User user, Post post);
    Optional<Like> findByUserIdAndPostId(Long userId, Long postId);

}
