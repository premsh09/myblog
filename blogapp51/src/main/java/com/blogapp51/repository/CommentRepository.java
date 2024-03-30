package com.blogapp51.repository;

import com.blogapp51.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPosterId(long posterId);
}
