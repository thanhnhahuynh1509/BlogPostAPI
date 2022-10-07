package com.htn.blog.exception;

public class BlogApiException extends RuntimeException {
	private String message;

	public BlogApiException(String message) {
		super(message);
	}
}
