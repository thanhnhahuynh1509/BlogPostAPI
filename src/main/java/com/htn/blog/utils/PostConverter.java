package com.htn.blog.utils;

import com.htn.blog.entity.Post;
import com.htn.blog.payload.PostDTO;

public class PostConverter {

	public static PostDTO convertToDTO(Post post) {
		PostDTO result = new PostDTO();
		result.setId(post.getId());
		result.setContent(post.getContent());
		result.setDescription(post.getDescription());
		result.setTitle(post.getTitle());
		return result;
	}
	
	public static Post convertToEntity(PostDTO postDTO) {
		Post result = new Post();
		result.setId(postDTO.getId());
		result.setContent(postDTO.getContent());
		result.setDescription(postDTO.getDescription());
		result.setTitle(postDTO.getTitle());
		return result;
	}
	
}
