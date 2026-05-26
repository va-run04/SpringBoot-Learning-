package com.springrest.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	//handle StudentNotFoundException
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorClass> handleStudentNotFoundException(StudentNotFoundException ex){
		ErrorClass error = new ErrorClass("404", ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	// handle all other exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorClass> handleException(Exception ex){
		
		ErrorClass error = new ErrorClass("500", ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
