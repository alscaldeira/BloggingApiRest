package com.caldeira.blog.controller.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.caldeira.blog.model.User;

public class LoginDto {

	public LoginDto() { }
	
	public LoginDto(User user) {
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
	}
	
	@NotBlank(message = "Username should not be blank")
	private String username;
	
	@NotBlank(message = "Password should not be blank")
	private String password;
	
	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(username, password);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
