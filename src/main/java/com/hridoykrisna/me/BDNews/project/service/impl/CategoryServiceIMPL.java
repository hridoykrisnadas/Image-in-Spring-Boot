package com.hridoykrisna.me.BDNews.project.service.impl;

import com.hridoykrisna.me.BDNews.auth.DB.Session;
import com.hridoykrisna.me.BDNews.auth.user.User;
import com.hridoykrisna.me.BDNews.project.controller.CategoryController;
import com.hridoykrisna.me.BDNews.project.model.Category;
import com.hridoykrisna.me.BDNews.project.model.dto.CategoryDto;
import com.hridoykrisna.me.BDNews.project.repository.CategoryRepo;
import com.hridoykrisna.me.BDNews.project.service.CategoryService;
import com.hridoykrisna.me.BDNews.util.ResponseBuilder;
import com.hridoykrisna.me.BDNews.util.ResponseDTO.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceIMPL implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    @Override
    public ResponseDto create(CategoryDto category) {
        Category category1 = modelMapper.map(category, Category.class);

        Session.user.ifPresent(user -> category1.setCreatedBy(user.getId()));
        if (category1 != null) {
            Category result = categoryRepo.save(category1);
            return ResponseBuilder.getSuccessMessage(HttpStatus.ACCEPTED, "Insert Success");
        } else {
            return ResponseBuilder.getFailureMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @Override
    public ResponseDto update(CategoryDto category) {
        var exist = categoryRepo.findById(category.getId());
        if (exist.isPresent()) {
            Category category1 = modelMapper.map(category, Category.class);
            category1.setIsActive(true);
            Session.user.ifPresent(user -> category1.setUpdateBy(user.getId()));
            var result = categoryRepo.save(category1);
            return ResponseBuilder.getSuccessMessage(HttpStatus.ACCEPTED, "Update Success");
        }
        return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "Id Not Found");
    }
        @Override
        public ResponseDto getAll () {
            List<Category> categoryList = categoryRepo.findAllByIsActiveTrue();
            List<CategoryDto> categoryDtoList = categoryDtos(categoryList);
            return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Successful", categoryDtoList);
        }

        @Override
        public ResponseDto getDetails ( int id){
            Category category = categoryRepo.findByIdAndIsActiveTrue(id);
            if (category != null) {
                CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
                return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Successful", categoryDto);
            } else {
                return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "Id Not Found");
            }
        }

    @Override
    public ResponseDto delete(int id) {
        Category category = categoryRepo.findByIdAndIsActiveTrue(id);
        if (category != null) {
            category.setIsActive(false);
            categoryRepo.save(category);
            return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Successfully Deleted this data.");
        } else {
            return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "Id Not Found");
        }
    }

    private List<CategoryDto> categoryDtos (List < Category > categoryList) {
            List<CategoryDto> categoryDtoList = new ArrayList<>();
            if ((long) categoryList.size() > 0) {
                categoryList.forEach(category -> {
                    modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
                    CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
                    categoryDtoList.add(categoryDto);
                });
            }
            return categoryDtoList;
        }
    }
