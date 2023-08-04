package com.hridoykrisna.me.BDNews.project.service;

import com.hridoykrisna.me.BDNews.project.model.dto.CategoryDto;
import com.hridoykrisna.me.BDNews.util.ResponseDTO.ResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface CategoryService {
    ResponseDto create(String s, CategoryDto category) throws IOException;

    ResponseDto update(CategoryDto category);

    ResponseDto getAll();

    ResponseDto getDetails(int id);

    ResponseDto delete(int id);

    ResponseDto create(String s, MultipartFile image, String name) throws IOException;
}
