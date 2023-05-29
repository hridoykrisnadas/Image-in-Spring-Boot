package com.hridoykrisna.me.BDNews.project.repository;

import com.hridoykrisna.me.BDNews.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

    Category findByIdAndIsActiveTrue(int id);
    List<Category> findAllByIsActiveTrue();

}
