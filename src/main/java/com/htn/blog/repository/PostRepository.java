package com.htn.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.htn.blog.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
