package com.hridoykrisna.me.BDNews.project.model.dto;

import com.hridoykrisna.me.BDNews.project.model.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private int id;
    @NotBlank(message = "Title is Mandatory")
    private String title;
    @NotBlank(message = "Text is Mandatory")
    private String mainText;
    private String featureImage;
    private List<CategoryDto> category;
}
