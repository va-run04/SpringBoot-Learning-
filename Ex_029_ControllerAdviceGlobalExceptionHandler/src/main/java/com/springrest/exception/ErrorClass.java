package com.springrest.exception;
import java.time.LocalDateTime;

public class ErrorClass {
	
	private String StatusCode;
	
	private String message;
	
	private LocalDateTime localDateTime;
	
	
	public ErrorClass(String StatusCode, String message, LocalDateTime localDateTime) {
		
		this.StatusCode = StatusCode;
		this.message = message;
		this.localDateTime = localDateTime;
		
	}


	public String getStatusCode() {
		return StatusCode;
	}


	public void setStatusCode(String statusCode) {
		StatusCode = statusCode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}


	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	
	
}
