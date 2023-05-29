package com.hridoykrisna.me.BDNews.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


import java.util.*;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_post")
public class Post extends BaseModel{

    private String title;
    private String mainText;
    private String featureImage;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE
    })
    @JoinTable(
            name = "post_category",
            joinColumns = @JoinColumn(
                    name = "post_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id"
            )
    )
    @JsonBackReference
    private List<Category> category;
}
