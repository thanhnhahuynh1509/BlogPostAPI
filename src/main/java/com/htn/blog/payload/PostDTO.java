package com.htn.blog.payload;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PostDTO {
	private long id;
	
	@NotEmpty(message = "Title should not be null or empty")
	@Size(min = 2, message = "Title should have at least 2 characters")
	private String title;
	
	@NotEmpty(message = "description should not be null or empty")
	@Size(min = 10, message = "description should have at least 10 characters")
	private String description;
	
	@NotEmpty(message = "content should not be null or empty")
	private String content;
	
	private Set<CommentDTO> comments;

	public PostDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDTO> comments) {
		this.comments = comments;
	}

}
