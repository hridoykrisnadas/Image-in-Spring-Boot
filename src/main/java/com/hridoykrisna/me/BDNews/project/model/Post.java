package com.hridoykrisna.me.BDNews.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Title is Mandatory")
    private String title;
    @NotBlank(message = "Text is Mandatory")
    private String mainText;
    private String featureImage;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.ALL
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
