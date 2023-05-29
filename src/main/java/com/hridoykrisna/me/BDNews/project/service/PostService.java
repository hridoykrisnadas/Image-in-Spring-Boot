package com.hridoykrisna.me.BDNews.project.service;

import com.hridoykrisna.me.BDNews.project.model.Post;
import com.hridoykrisna.me.BDNews.project.model.dto.PostDto;
import com.hridoykrisna.me.BDNews.util.ResponseDTO.ResponseDto;

public interface PostService {
    ResponseDto save(PostDto postDto);

    ResponseDto getAll();
}
