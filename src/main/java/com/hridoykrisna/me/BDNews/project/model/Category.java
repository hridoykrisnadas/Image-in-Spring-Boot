package com.hridoykrisna.me.BDNews.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

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
