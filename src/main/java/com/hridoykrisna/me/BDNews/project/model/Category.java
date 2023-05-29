package com.hridoykrisna.me.BDNews.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_category")
public class Category extends BaseModel {
    private int id;
    private String name;
    @ManyToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE
    })
    @JsonIgnore
    private List<Post> post;
}
