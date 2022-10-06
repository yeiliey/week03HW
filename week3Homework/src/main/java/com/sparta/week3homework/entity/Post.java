package com.sparta.week3homework.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    private Long id;

    private String title;

    private String author;

    private String content;

    private String password;


    public Post(String title, String author, String content, String password) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.password = password;
    }

    public void update(String title, String author, String content, String password) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.password = password;

    }

    public boolean pwMatch(String password) {

        return this.password.equals(password);
    }
}
