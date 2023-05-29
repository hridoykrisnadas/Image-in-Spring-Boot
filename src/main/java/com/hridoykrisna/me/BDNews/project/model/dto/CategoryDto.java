package com.hridoykrisna.me.BDNews.project.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private int id;
    @NotBlank(message = "Name is Mandatory")
    private String name;
}