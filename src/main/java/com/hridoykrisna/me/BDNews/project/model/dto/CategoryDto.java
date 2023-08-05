package com.hridoykrisna.me.BDNews.project.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private int id;
    @NotBlank(message = "Name is Mandatory")
    private String name;
    @NotBlank(message = "Image is Mandatory")
    private String image;
}
