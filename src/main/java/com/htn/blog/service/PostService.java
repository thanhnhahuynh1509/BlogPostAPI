package com.htn.blog.service;

import com.htn.blog.payload.PostDTO;
import com.htn.blog.payload.PostResponse;

public interface PostService {
	PostDTO createPost(PostDTO postDTO);
	PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir);
	PostDTO getPost(long id);
	PostDTO update(PostDTO postDTO, long id);
	void delete(long id);
}
