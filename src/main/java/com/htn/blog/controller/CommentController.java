package com.htn.blog.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.htn.blog.payload.CommentDTO;
import com.htn.blog.service.CommentService;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

	private final CommentService cmtService;

	public CommentController(CommentService cmtService) {
		super();
		this.cmtService = cmtService;
	}
	
	// CREATE
	
	@PostMapping
	public ResponseEntity<CommentDTO> save(@PathVariable(name = "postId") long postId, @Valid @RequestBody CommentDTO cmtDTO) {
		return new ResponseEntity<>(cmtService.save(postId, cmtDTO), HttpStatus.CREATED);
	}
	
	// READ
	
	@GetMapping
	public ResponseEntity<Set<CommentDTO>> getComments(@PathVariable(name = "postId") long postId) {
		return new ResponseEntity<>(cmtService.getCommentsByPostId(postId), HttpStatus.OK);
	}
	
	@GetMapping("/{cmtId}")
	public ResponseEntity<CommentDTO> getComment(@PathVariable(name = "postId") long postId, @PathVariable(name = "cmtId") long cmtId) {
		return new ResponseEntity<>(cmtService.getComment(postId, cmtId), HttpStatus.OK);
	}
	
	// UPDATE
	
	@PutMapping("/{cmtId}")
	public ResponseEntity<CommentDTO> update(
			@PathVariable(name = "postId") long postId, 
			@PathVariable(name = "cmtId") long cmtId,
			@Valid @RequestBody CommentDTO cmtDTO
	) {
		return new ResponseEntity<>(cmtService.update(postId, cmtId, cmtDTO), HttpStatus.OK);
	}
	
	// DELETE
	@DeleteMapping("/{cmtId}")
	public ResponseEntity<String> delete(
			@PathVariable(name = "postId") long postId, 
			@PathVariable(name = "cmtId") long cmtId
	) {
		cmtService.delete(postId, cmtId);
		return new ResponseEntity<>("Comment was deleted successfully", HttpStatus.OK);
	}
	
}
