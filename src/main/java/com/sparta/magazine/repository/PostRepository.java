package com.sparta.magazine.repository;

import com.sparta.magazine.model.Like;
import com.sparta.magazine.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p join fetch p.likes join fetch p.user")
    List<Post> findAllByOrderByCreatedAtDesc(Sort sort);
}
