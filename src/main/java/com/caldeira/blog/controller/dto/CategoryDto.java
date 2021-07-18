package com.caldeira.blog.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.caldeira.blog.model.Category;

public class CategoryDto {

	private Long id;
	private String name;
	private String description;
	private List<Long> posts;
	
	public CategoryDto() { }

	public CategoryDto(Category category) {
		this.setDescription(category.getDescription());
		this.setId(category.getId());
		this.setName(category.getName());
		
		this.posts = new ArrayList<Long>();
		
		if(category != null || !category.equals(null)) {
			for(int i=0; i < category.getPosts().size(); i++) {
				this.posts.add(category.getPosts().get(i).getId());
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Long> getPosts() {
		return posts;
	}

	public void setPosts(List<Long> posts) {
		this.posts = posts;
	}
}
