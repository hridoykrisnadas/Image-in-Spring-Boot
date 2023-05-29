package com.hridoykrisna.me.BDNews.project.controller;

import com.hridoykrisna.me.BDNews.project.model.dto.CategoryDto;
import com.hridoykrisna.me.BDNews.util.ResponseDTO.ResponseDto;
import com.hridoykrisna.me.BDNews.project.service.CategoryService;
import com.hridoykrisna.me.BDNews.util.ResponseBuilder;
import com.hridoykrisna.me.BDNews.util.UrlConstraint;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(UrlConstraint.CategoryManagement.ROOT)
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping(UrlConstraint.CategoryManagement.CREATE)
    public ResponseDto createProduct(@Valid @RequestBody CategoryDto categoryDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseBuilder.getFailureMessage(result, "Bean Binding Error");
        }
        return categoryService.save(categoryDto);
    }

    @PutMapping(UrlConstraint.CategoryManagement.UPDATE)
    public ResponseDto updateProduct(@Valid @RequestBody CategoryDto categoryDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseBuilder.getFailureMessage(result, "Bean Binding Error");
        }
        return categoryService.update(categoryDto);
    }

    @DeleteMapping(UrlConstraint.CategoryManagement.DELETE)
    public ResponseDto deleteProduct(@PathVariable("id") int id) {
        return categoryService.delete(id);
    }

    @GetMapping(UrlConstraint.CategoryManagement.DETAILS)
    public ResponseDto getProduct(@PathVariable("id") int id) {
        return categoryService.getDetails(id);
    }

    @GetMapping(UrlConstraint.CategoryManagement.ALL_CATEGORY)
    public ResponseDto getAllProduct() {
        return categoryService.getAll();
    }
}
