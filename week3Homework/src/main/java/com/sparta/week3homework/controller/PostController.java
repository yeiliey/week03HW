package com.sparta.week3homework.controller;


import com.sparta.week3homework.dto.*;
import com.sparta.week3homework.entity.Post;
import com.sparta.week3homework.repository.PostRepository;
import com.sparta.week3homework.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller // html
@RestController //json
@RequiredArgsConstructor

@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    //게시글 전체조회
    @GetMapping("/posts")
    public List<PostResDto> getAll() {

        return postService.getAll();
    }

    //게시글 하나 조회
    @GetMapping("/posts/{id}")
    public PostResDto getOneById(@PathVariable("id") Long id) {

        return postService.getOne(id);
    }


    //게시글 생성
    //http body 에 데이터가 담긴다.
    @PostMapping("/posts")
    public PostResDto createPost(@RequestBody PostReqDto postReqDto) {

        return postService.create(postReqDto);
    }


    // 비밀번호 확인
    // Http body에 데이터가 담겨있다.
    @PostMapping("/posts/{id}")
    public PasswordCheckResDto passwordCheck(@PathVariable("id") Long id, @RequestBody PasswordCheckReqDto passwordCheckReqDto) {
        return postService.pwCheck(id, passwordCheckReqDto);
    }

    // 게시글 수정
    // 게시글 id, 바꿀 데이터가 필요함!
    @PutMapping("/posts/{id}")
    public PostResDto updatePost(@PathVariable("id") Long id, @RequestBody PostReqDto postReqDto) {

        return postService.update(id, postReqDto);
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public PostDelResDto deletePost(@PathVariable("id") Long id) {

        return postService.delete(id);
    }

}
















