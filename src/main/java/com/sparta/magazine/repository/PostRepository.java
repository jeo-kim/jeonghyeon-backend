package com.sparta.magazine.repository;

import com.sparta.magazine.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select distinct p from Post p left join fetch p.likes left join fetch p.user")
    List<Post> findAllFetched(Sort sort);
}
