package com.hridoykrisna.me.BDNews.project.repository;

import com.hridoykrisna.me.BDNews.project.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {

}
