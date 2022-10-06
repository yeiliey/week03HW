package com.sparta.week3homework.dto;


import com.sparta.week3homework.entity.Post;

import java.time.LocalDateTime;

public class PostResDto {

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long id;

    private String title;

    private String author;


    public PostResDto(Post post){

        createdAt = post.getCreatedAt();

        updatedAt = post.getUpdateAt();

        id = post.getId();

        title = post.getTitle();

        author = post.getAuthor();

    }

}
