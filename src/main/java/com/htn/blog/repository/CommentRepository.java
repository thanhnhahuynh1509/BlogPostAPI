package com.htn.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htn.blog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
