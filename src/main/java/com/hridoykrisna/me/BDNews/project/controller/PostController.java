package com.hridoykrisna.me.BDNews.project.controller;

import com.hridoykrisna.me.BDNews.project.model.Post;
import com.hridoykrisna.me.BDNews.project.model.dto.PostDto;
import com.hridoykrisna.me.BDNews.project.service.PostService;
import com.hridoykrisna.me.BDNews.util.ResponseBuilder;
import com.hridoykrisna.me.BDNews.util.ResponseDTO.ResponseDto;
import com.hridoykrisna.me.BDNews.util.UrlConstraint;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(UrlConstraint.PostManagement.ROOT)
public class PostController {
    public final PostService postService;

    @PostMapping(UrlConstraint.PostManagement.CREATE)
    public ResponseDto createPost(@Valid @RequestBody PostDto postDto, BindingResult result){
        if (result.hasErrors()){
            return ResponseBuilder.getFailureMessage(result, "Bean Binding Error");
        }
        System.out.println("Get Data: "+ postDto.toString());
        return postService.save(postDto);

    }
    @GetMapping(UrlConstraint.PostManagement.ALL_POST)
    public ResponseDto getAllPost(){
        return postService.getAll();
    }
}
