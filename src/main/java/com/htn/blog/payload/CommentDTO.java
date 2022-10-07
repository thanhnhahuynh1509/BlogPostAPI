package com.htn.blog.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CommentDTO {

	private long id;
	
	@NotEmpty(message = "body should not be null or empty")
	@Size(min = 2, message = "body should have at least 2 characters")
	private String body;
	
	@NotEmpty(message = "email should not be null or empty")
	@Size(min = 2, message = "email should have at least 2 characters")
	private String email;
	
	@NotEmpty(message = "name should not be null or empty")
	@Size(min = 2, message = "name should have at least 2 characters")
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
