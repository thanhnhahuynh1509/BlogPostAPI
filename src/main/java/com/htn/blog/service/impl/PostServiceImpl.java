package com.htn.blog.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.htn.blog.entity.Post;
import com.htn.blog.exception.ResourceNotFoundException;
import com.htn.blog.payload.PostDTO;
import com.htn.blog.payload.PostResponse;
import com.htn.blog.repository.PostRepository;
import com.htn.blog.service.PostService;
import com.htn.blog.utils.PostConverter;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final ModelMapper modelMapper;

	public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public PostDTO createPost(PostDTO postDTO) {
		Post post = modelMapper.map(postDTO, Post.class);
		post = postRepository.save(post);
		return modelMapper.map(post, PostDTO.class);
	}

	@Override
	public PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable page = PageRequest.of(pageNo, pageSize, sort);
		Page<Post> postsPage = postRepository.findAll(page);

		List<Post> posts = postsPage.getContent();
		List<PostDTO> postsDTO = posts.stream().map(p -> modelMapper.map(p, PostDTO.class)).toList();

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postsDTO);
		postResponse.setPageNo(postsPage.getNumber());
		postResponse.setPageSize(postsPage.getSize());
		postResponse.setTotalElements(postsPage.getTotalElements());
		postResponse.setTotalPages(postsPage.getTotalPages());
		postResponse.setLast(postsPage.isLast());
		return postResponse;
	}

	@Override
	public PostDTO getPost(long id) {
		Post post = getPostDb(id);
		return modelMapper.map(post, PostDTO.class);
	}

	@Override
	public PostDTO update(PostDTO postDTO, long id) {
		Post post = getPostDb(id);
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setContent(postDTO.getContent());
		post = postRepository.save(post);
		return modelMapper.map(post, PostDTO.class);
	}

	@Override
	public void delete(long id) {
		Post post = getPostDb(id);
		postRepository.delete(post);
	}

	private Post getPostDb(long id) {
		return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id + ""));
	}

}
