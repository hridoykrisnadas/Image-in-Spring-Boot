package com.hridoykrisna.me.BDNews.project.model.dto;

import com.hridoykrisna.me.BDNews.project.model.BaseModel;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CategoryDto {
    private int id;
    @NotBlank(message = "Name is Mandatory")
    private String name;
}
