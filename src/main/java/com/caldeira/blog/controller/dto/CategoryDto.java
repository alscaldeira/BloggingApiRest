package com.caldeira.blog.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.caldeira.blog.model.Category;

public class CategoryDto {

	private String name;
	private List<Long> posts = new ArrayList<Long>();
	
	public CategoryDto() { }

	public CategoryDto(Category category) {
		this.setName(category.getName());
		
		if(category.getPosts() != null) {
			for(int i=0; i < category.getPosts().size(); i++) {
				this.posts.add(category.getPosts().get(i).getId());
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getPosts() {
		return posts;
	}

	public void setPosts(List<Long> posts) {
		this.posts = posts;
	}
}
