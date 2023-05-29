package com.hridoykrisna.me.BDNews.project.service;

import com.hridoykrisna.me.BDNews.project.model.dto.CategoryDto;
import com.hridoykrisna.me.BDNews.util.ResponseDTO.ResponseDto;

public interface CategoryService {
    ResponseDto save(CategoryDto categoryDto);

    ResponseDto update(CategoryDto categoryDto);

    ResponseDto delete(int Id);

    ResponseDto getDetails(int Id);

    ResponseDto getAll();
}
