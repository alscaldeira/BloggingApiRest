package com.caldeira.blog.controller.dto;

import java.util.List;

import com.caldeira.blog.model.Post;

public class PostDto {

	private Long id;
	private String text;
	private Byte[] image;
	private String title;
	private List<String> categories;
	private Long user;
	private Long views;
	
	public PostDto() { }

	public PostDto(Post post) {
		this.setId(post.getId());
		this.setImage(post.getImage());
		this.setText(post.getText());
		this.setTitle(post.getTitle());
		this.setUser(post.getUser().getId());
		this.setViews(post.getViews());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Byte[] getImage() {
		return image;
	}
	public void setImage(Byte[] image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public Long getUser() {
		return user;
	}
	public void setUser(Long user) {
		this.user = user;
	}
	public Long getViews() {
		return views;
	}
	public void setViews(Long views) {
		this.views = views;
	}
}
