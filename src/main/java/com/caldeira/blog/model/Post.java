package com.caldeira.blog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.caldeira.blog.controller.dto.PostDto;
import com.caldeira.blog.repository.CategoryRepository;
import com.caldeira.blog.repository.UserRepository;

@Entity
public class Post {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String text;
	@Lob
	private Byte[] image;
	private String title;
	private Long views;
	
	@ManyToMany(mappedBy = "posts")
	private List<Category> categories;
	
	@ManyToOne
	private User user;
	
	public Post() { }
	
	public Post(PostDto postDto, UserRepository userRepository, CategoryRepository categoryRepository) {
		this.setId(postDto.getId());
		this.setImage(postDto.getImage());
		this.setText(postDto.getText());
		this.setTitle(postDto.getTitle());
		this.setUser(userRepository.findById(postDto.getUser()).get());
		this.setViews(postDto.getViews());
		
		// GETTING CATEGORIES FROM POST
		if(postDto.getCategories() != null) {
			if(postDto.getCategories().size() > 0) {
				
				List<Category> listOfCategories = new ArrayList<Category>();
				for(Long i=(long) 0; i < postDto.getCategories().size(); i++) {
					listOfCategories.add(categoryRepository.findById(i).get());
				}
				this.setCategories(listOfCategories);
			}
		}
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
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getViews() {
		return views;
	}
	public void setViews(Long views) {
		this.views = views;
	}
}
