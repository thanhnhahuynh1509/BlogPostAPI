package com.htn.blog.service;

import java.util.Set;

import com.htn.blog.payload.CommentDTO;

public interface CommentService {
	
	CommentDTO save(long postId, CommentDTO commentDTO);
	Set<CommentDTO> getCommentsByPostId(long postId);
	CommentDTO getComment(long postId, long cmtId);
	CommentDTO update(long postId, long cmtId, CommentDTO cmtDTO);
	void delete(long postId, long cmtId);
}
