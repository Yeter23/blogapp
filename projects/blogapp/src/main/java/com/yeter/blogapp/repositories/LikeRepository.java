package com.yeter.blogapp.repositories;

import com.yeter.blogapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
