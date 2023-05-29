package com.hridoykrisna.me.BDNews.auth.repository;

import com.hridoykrisna.me.BDNews.auth.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
