package com.htn.blog.utils;

import com.htn.blog.entity.Comment;
import com.htn.blog.payload.CommentDTO;

public class CommentConverter {

	public static CommentDTO convertToDTO(Comment comment) {
		CommentDTO result = new CommentDTO();
		result.setId(comment.getId());
		result.setBody(comment.getBody());
		result.setName(comment.getName());
		result.setEmail(comment.getEmail());
		return result;
	}
	
	public static Comment convertToEntity(CommentDTO commentDTO) {
		Comment result = new Comment();
		result.setId(commentDTO.getId());
		result.setBody(commentDTO.getBody());
		result.setName(commentDTO.getName());
		result.setEmail(commentDTO.getEmail());
		return result;
	}
	
}
