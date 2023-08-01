package com.hridoykrisna.me.BDNews.project.service;

import com.hridoykrisna.me.BDNews.project.controller.CategoryController;
import com.hridoykrisna.me.BDNews.project.model.dto.CategoryDto;
import com.hridoykrisna.me.BDNews.util.ResponseDTO.ResponseDto;


public interface CategoryService {
    ResponseDto create(CategoryDto category);

    ResponseDto update(CategoryDto category);

    ResponseDto getAll();

    ResponseDto getDetails(int id);

    ResponseDto delete(int id);
}
