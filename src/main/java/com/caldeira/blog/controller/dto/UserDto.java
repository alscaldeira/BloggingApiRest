package com.caldeira.blog.controller.dto;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.caldeira.blog.model.User;

public class UserDto {
	
	private String username;
	private String name;
	private String lastName;
	private String password;
	private String email;
	
	public UserDto() { }
	
	public UserDto(User user) {
		this.setLastName(user.getLastName());
		this.setName(user.getName());
		this.setUsername(user.getUsername());
		this.setEmail(user.getEmail());
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(username, password);
	}
	
}
