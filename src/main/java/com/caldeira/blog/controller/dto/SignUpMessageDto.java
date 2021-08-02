package com.caldeira.blog.controller.dto;

public class SignUpMessageDto {

	private String message; 
	
	public SignUpMessageDto(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
