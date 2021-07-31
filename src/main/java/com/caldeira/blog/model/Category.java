package com.caldeira.blog.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.caldeira.blog.controller.dto.CategoryDto;
import com.caldeira.blog.repository.CategoryRepository;
import com.caldeira.blog.repository.PostRepository;

@Entity
public class Category {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Post> posts;
	
	public Category() { }
	
	public Category(String name) {
		this.name = name;
	}
	
	public Category(CategoryDto categoryDto, PostRepository postRepository, CategoryRepository categoryRepository) {
		Optional<Category> category = categoryRepository.findByName(categoryDto.getName());
		
		if(category.isPresent()) {
			this.id = category.get().getId();
			this.name = categoryDto.getName();
			this.posts = new ArrayList<Post>();
		} else {
			Category categorySaved = categoryRepository.save(new Category(categoryDto.getName()));
			this.id = categorySaved.getId();
			this.name = categorySaved.getName();
			this.posts = categorySaved.getPosts();
		}
		
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
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
