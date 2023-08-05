package com.hridoykrisna.me.BDNews.project.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "_category")
public class Category extends BaseModel{
    private String name;
    private String image;
}
