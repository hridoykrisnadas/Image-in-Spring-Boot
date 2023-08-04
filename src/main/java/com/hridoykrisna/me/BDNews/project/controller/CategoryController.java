package com.hridoykrisna.me.BDNews.project.controller;

import com.hridoykrisna.me.BDNews.project.model.dto.CategoryDto;
import com.hridoykrisna.me.BDNews.project.repository.CategoryRepo;
import com.hridoykrisna.me.BDNews.project.service.CategoryService;
import com.hridoykrisna.me.BDNews.project.service.FileService;
import com.hridoykrisna.me.BDNews.util.ResponseBuilder;
import com.hridoykrisna.me.BDNews.util.ResponseDTO.ResponseDto;
import com.hridoykrisna.me.BDNews.util.UrlConstraint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping(UrlConstraint.CategoryManagement.ROOT)
public class CategoryController {
    private final CategoryService categoryService;
    private final FileService fileService;

    @Value("${project.images}")
    private String path;
    @PostMapping(UrlConstraint.CategoryManagement.CREATE)
    public ResponseDto createCategory(@Valid @RequestPart("image") MultipartFile image,
                                      @RequestPart("name") String name,
                                      BindingResult result) throws IOException {
        if (result.hasErrors()){
            return ResponseBuilder.getFailureMessage(result, "Bean Binding Result");
        } else{

            System.out.println(image+" : "+name);
            return categoryService.create(path, image, name);
        }
    }

    @GetMapping(value = "/images/{image}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("image") String imageName,
                              HttpServletResponse response)
            throws IOException {

        InputStream stream = this.fileService.getResources(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(stream, response.getOutputStream());
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
