package com.htn.blog.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.htn.blog.entity.Comment;
import com.htn.blog.entity.Post;
import com.htn.blog.exception.BlogApiException;
import com.htn.blog.exception.ResourceNotFoundException;
import com.htn.blog.payload.CommentDTO;
import com.htn.blog.repository.CommentRepository;
import com.htn.blog.repository.PostRepository;
import com.htn.blog.service.CommentService;
import com.htn.blog.utils.CommentConverter;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentRepository cmtRepository;
	private final PostRepository postRepository;
	private final ModelMapper modelMapper;

	public CommentServiceImpl(CommentRepository cmtRepository, PostRepository postRepository, ModelMapper modelMapper) {
		super();
		this.cmtRepository = cmtRepository;
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CommentDTO save(long postId, CommentDTO commentDTO) {
		Post post = getPost(postId);
		Comment cmt = modelMapper.map(commentDTO, Comment.class);
		cmt.setPost(post);
		cmt = cmtRepository.save(cmt);
		return modelMapper.map(cmt, CommentDTO.class);
	}

	@Override
	public Set<CommentDTO> getCommentsByPostId(long postId) {
		Post post = getPost(postId);
		Set<CommentDTO> commetsDTO = post.getComments().stream().map(c -> modelMapper.map(c, CommentDTO.class))
				.collect(Collectors.toSet());
		return commetsDTO;
	}

	@Override
	public CommentDTO getComment(long postId, long cmtId) {
		Post post = getPost(postId);
		Comment cmt = getComment(cmtId);
		checkCommentBelongToPost(cmt, post);
		
		return modelMapper.map(cmt, CommentDTO.class);
	}

	@Override
	public CommentDTO update(long postId, long cmtId, CommentDTO cmtDTO) {
		Post post = getPost(postId);
		Comment cmt = getComment(cmtId);
		checkCommentBelongToPost(cmt, post);
		
		cmt.setBody(cmtDTO.getBody());
		cmt.setEmail(cmtDTO.getEmail());
		cmt.setName(cmtDTO.getName());
		cmt = cmtRepository.save(cmt);
		return modelMapper.map(cmt, CommentDTO.class);
	}
	
	@Override
	public void delete(long postId, long cmtId) {
		Post post = getPost(postId);
		Comment cmt = getComment(cmtId);
		checkCommentBelongToPost(cmt, post);
		
		cmtRepository.delete(cmt);
	}

	private void checkCommentBelongToPost(Comment cmt, Post post) {
		if(!cmt.getPost().getId().equals(post.getId()))
			throw new BlogApiException("Comment does not belong to Post");
	}
	
	private Post getPost(long postId) {
		return postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId + ""));
	}

	private Comment getComment(long cmtId) {
		return cmtRepository.findById(cmtId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", cmtId + ""));
	}

	

}
