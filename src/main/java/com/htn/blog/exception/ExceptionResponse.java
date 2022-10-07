package com.htn.blog.exception;

import java.util.Date;

public class ExceptionResponse {

	
	private String message;
	private String description;
	private Date timestamp;
	private int status;

	public ExceptionResponse(String message, int status, Date timestamp, String description) {
		super();
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
		this.description = description;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
