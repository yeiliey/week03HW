package com.sparta.week3homework.repository;

import com.sparta.week3homework.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {
}
