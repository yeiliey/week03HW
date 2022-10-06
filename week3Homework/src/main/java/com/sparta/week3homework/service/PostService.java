package com.sparta.week3homework.service;


import com.sparta.week3homework.dto.*;
import com.sparta.week3homework.entity.Post;
import com.sparta.week3homework.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    //Repository에서 List<Post>로 받아온 데이터들을
    // List<PostResponseDto> 로 변환시켜준 다음, 리턴한다.
    public List<PostResDto> getAll() {


        //[1, 2, 3, 4, 5]
        //["1", "2", "3", "4", "5"]
        List<PostResDto> result = new ArrayList<>();
        List<Post> postList = postRepository.findAll();

        for (Post post : postList) {
            PostResDto postResDto = new PostResDto(post);
            result.add(postResDto);
        }
        return result;
    }


    // 게시글 하나 조회 : id 가 필요함 !
    public PostResDto getOne(Long id) {

        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            return new PostResDto(optionalPost.get());
        }
    }

    // 게시글 저장 : 게시글 데이터가 필요함 !
    @Transactional
    public PostResDto create(PostReqDto postReqDto) {

        Post post = postReqDto.toEntity();
        postRepository.save(post);
        return new PostResDto(post);
    }

    // 비밀번호 확인
    //1. 확인할 게시물을 가져온다.
    //2. 비교한다.
    public PasswordCheckResDto pwCheck(Long id, PasswordCheckReqDto passwordCheckReqDto) {
        Optional<Post> optionalPost = postRepository.findById(id);

        // 값이 없다면
        if(optionalPost.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            // 내부에 값이 존재 하므로 값을 꺼낸다.
            Post post = optionalPost.get();

            // 비밀번호 값이 일치하는지 확인한다.
            boolean match = post.pwMatch(passwordCheckReqDto.getPassword());

            // 원하는 리턴타입을 만든 후 리턴한다.
            return new PasswordCheckResDto(match);
        }
    }

    // 게시글 수정
    //1. db에 저장된 수정할 게시글을 가져온다.
    //2. 게시글을 수정한다.
    //3. db 에 수정된 게시글을 저장한다.
    public PostResDto update(Long id, PostReqDto postReqDto) {
        Optional<Post> optionalPost = postRepository.findById(id);

        // 게시글이 없다면
        if(optionalPost.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            Post post = optionalPost.get();

            post.update(
                    postReqDto.getTitle(),
                    postReqDto.getAuthor(),
                    postReqDto.getContent(),
                    postReqDto.getPassword()
            );

            postRepository.save(post);
            return new PostResDto(post);
        }
    }

    // 게시글 삭제
    public PostDelResDto delete(Long id) {

        postRepository.deleteById(id);
        return new PostDelResDto(true);
    }
}












