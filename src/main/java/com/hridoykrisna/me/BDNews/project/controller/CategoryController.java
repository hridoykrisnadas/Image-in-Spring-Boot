package com.hridoykrisna.me.BDNews.project.controller;

import com.hridoykrisna.me.BDNews.project.model.dto.CategoryDto;
import com.hridoykrisna.me.BDNews.project.repository.CategoryRepo;
import com.hridoykrisna.me.BDNews.project.service.CategoryService;
import com.hridoykrisna.me.BDNews.util.ResponseBuilder;
import com.hridoykrisna.me.BDNews.util.ResponseDTO.ResponseDto;
import com.hridoykrisna.me.BDNews.util.UrlConstraint;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(UrlConstraint.CategoryManagement.ROOT)
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping(UrlConstraint.CategoryManagement.CREATE)
    public ResponseDto createCategory(@Valid @RequestBody CategoryDto categoryDto, BindingResult result){
        if (result.hasErrors()){
            return ResponseBuilder.getFailureMessage(result, "Bean Binding Result");
        } else{
            return categoryService.create(categoryDto);
        }
    }

    @PutMapping(UrlConstraint.CategoryManagement.UPDATE)
    public ResponseDto updateCategory(@Valid @RequestBody CategoryDto categoryDto, BindingResult result){
        if (result.hasErrors()){
            return ResponseBuilder.getFailureMessage(result, "Bean Binding Result");
        } else{
            return categoryService.update(categoryDto);
        }
    }
    @GetMapping(UrlConstraint.CategoryManagement.ALL_CATEGORY)
    public ResponseDto getAllCategory(){
        return categoryService.getAll();
    }

    @GetMapping(UrlConstraint.CategoryManagement.DETAILS)
    public ResponseDto getCategoryDetails(@Valid @PathVariable("id") int id){
        return categoryService.getDetails(id);
    }

    @DeleteMapping(UrlConstraint.CategoryManagement.DELETE)
    public ResponseDto deleteCategory(@Valid @PathVariable("id") int id){
        return categoryService.delete(id);
    }
}
