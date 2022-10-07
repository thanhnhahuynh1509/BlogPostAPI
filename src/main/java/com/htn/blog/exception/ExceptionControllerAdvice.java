package com.htn.blog.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ExceptionResponse handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
		return new ExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(), new Date(), webRequest.getDescription(false));
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ExceptionResponse handleBlogApiException(BlogApiException ex, WebRequest webRequest) {
		return new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), new Date(), webRequest.getDescription(false));
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse handleGlobalException(Exception ex, WebRequest webRequest) {
		return new ExceptionResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), webRequest.getDescription(false));
	}

//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		Map<String, String> errors = new HashMap<>();
//		ex.getBindingResult().getAllErrors().forEach(e -> {
//			String fieldName = ((FieldError) e).getField();
//			String message = e.getDefaultMessage();
//			errors.put(fieldName, message);
//		});
//		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//	}
	
	@ExceptionHandler
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest webRequest) {
		Map<String, String> errorsResponse = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errorsResponse.put(fieldName, message);
		});
		return new ResponseEntity<>(errorsResponse, HttpStatus.BAD_REQUEST);
	}
	
	
}
