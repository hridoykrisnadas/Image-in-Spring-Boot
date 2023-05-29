package com.hridoykrisna.me.BDNews.project.service.impl;

import com.hridoykrisna.me.BDNews.auth.DB.Session;
import com.hridoykrisna.me.BDNews.project.model.Category;
import com.hridoykrisna.me.BDNews.project.model.dto.CategoryDto;
import com.hridoykrisna.me.BDNews.util.ResponseDTO.ResponseDto;
import com.hridoykrisna.me.BDNews.project.repository.CategoryRepo;
import com.hridoykrisna.me.BDNews.project.service.CategoryService;
import com.hridoykrisna.me.BDNews.util.ResponseBuilder;
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
    public ResponseDto save(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        if (Session.user.isPresent())
            category.setCreatedBy(Session.user.get().getId());
        category = categoryRepo.save(category);

        if (category != null) {
            return ResponseBuilder.getSuccessMessage(HttpStatus.CREATED, "Category Saved", category);
        }
        return ResponseBuilder.getFailureMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    @Override
    public ResponseDto update(CategoryDto categoryDto) {
        Category category = categoryRepo.findByIdAndIsActiveTrue(categoryDto.getId());
        if (category != null) {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            modelMapper.map(categoryDto, category);
            category.setUpdateBy(Session.user.get().getId());
            category = categoryRepo.save(category);
            if (category != null) {
                return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Category Update", category);
            }
            return ResponseBuilder.getFailureMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
        return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "Not Found!!");
    }

    @Override
    public ResponseDto delete(int Id) {
        Category category = categoryRepo.findByIdAndIsActiveTrue(Id);
        if (category != null) {
            category.setIsActive(false);
            category = categoryRepo.save(category);
            if (category != null) {
                return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Category Deleted", category);
            }
            return ResponseBuilder.getFailureMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
        return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "Not Found!!");
    }

    @Override
    public ResponseDto getDetails(int Id) {
        Category category = categoryRepo.findByIdAndIsActiveTrue(Id);
        if (category != null) {
            return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Successful", category);
        }
        return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "Not Found!!");
    }

    @Override
    public ResponseDto getAll() {
        List<Category> categoryList = categoryRepo.findAllByIsActiveTrue();
        List<CategoryDto> categoryDtoList = this.getcategoryDtos(categoryList);
        int numberOfElement = categoryDtoList.get(0).getClass().getDeclaredFields().length;
        return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Successful", categoryDtoList, numberOfElement, categoryDtoList.size());
    }

    private List<CategoryDto> getcategoryDtos(List<Category> categories) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categories.forEach(category -> {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
            categoryDtoList.add(categoryDto);

        });
        return categoryDtoList;
    }
}
