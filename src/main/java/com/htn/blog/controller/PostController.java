package com.htn.blog.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htn.blog.payload.PostDTO;
import com.htn.blog.payload.PostResponse;
import com.htn.blog.service.PostService;
import com.htn.blog.utils.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}

	// CREATE

	@PostMapping
	public ResponseEntity<PostDTO> create(@Valid @RequestBody PostDTO postDTO) {
		return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
	}

	// READ

	@GetMapping
	public ResponseEntity<PostResponse> getPosts(
			@RequestParam(name = "pageNo", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NO) int pageNo,
			@RequestParam(name = "pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
			@RequestParam(name = "sortBy", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
			@RequestParam(name = "sortDir", required = false, defaultValue = AppConstants.DEFAULT_SORT_DIR) String sortDir
	) {
		
		return new ResponseEntity<>(postService.getPosts(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
	}	

	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getPost(@PathVariable(name = "id") long id) {
		return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
	}

	// UPDATE

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PostDTO> update(@Valid @RequestBody PostDTO postDTO, @PathVariable(name = "id") long id) {
		return new ResponseEntity<>(postService.update(postDTO, id), HttpStatus.OK);
	}


	// DELETE

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable(name = "id") long id) {
		postService.delete(id);
		return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
	}

}
