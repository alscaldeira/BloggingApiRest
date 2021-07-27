package com.caldeira.blog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.caldeira.blog.controller.dto.CategoryDto;
import com.caldeira.blog.repository.PostRepository;

@Entity
public class Category {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Post> posts;
	
	public Category() { }
	
	public Category(CategoryDto categoryDto, PostRepository postRepository) {
		this.id = categoryDto.getId();
		this.name = categoryDto.getName();
		this.description = categoryDto.getDescription();
		this.posts = new ArrayList<Post>();
		
		//ADD CATEGORIES TO THIS.POST
		if(categoryDto.getPosts() != null || !categoryDto.getPosts().equals(null)) {
			for(int i=0; i < categoryDto.getPosts().size(); i++) {
				this.posts.add(postRepository.findById(categoryDto.getPosts().get(i)).get());
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
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
