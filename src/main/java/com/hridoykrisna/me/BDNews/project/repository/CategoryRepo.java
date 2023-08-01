package com.hridoykrisna.me.BDNews.project.repository;

import com.hridoykrisna.me.BDNews.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Category findByIdAndIsActiveTrue(int id);
    List<Category> findAllByIsActiveTrue();
}
