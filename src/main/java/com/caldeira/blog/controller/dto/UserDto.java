package com.caldeira.blog.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.caldeira.blog.model.User;

public class UserDto {
	
	private Long id;
	private String username;
	private String name;
	private String lastName;
	private String password;
	private List<Long> posts = new ArrayList<Long>();
	private Boolean active;
	private String email;
	
	public UserDto() { }
	
	public UserDto(User user) {
		this.setId(user.getId());
		this.setLastName(user.getLastName());
		this.setName(user.getName());
		this.setPassword(user.getPassword());
		this.setUsername(user.getUsername());
		this.setActive(user.getActive());
		this.setEmail(user.getEmail());
		
		// ADD POSTS FROM USER.POSTS
		if(user.getPosts() != null) {
			user.getPosts().forEach( post -> {
				this.posts.add(post.getId());
			});
		}		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public List<Long> getPosts() {
		return posts;
	}
	public void setPosts(List<Long> postId) {
		this.posts = postId;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
