package com.caldeira.blog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.caldeira.blog.controller.dto.UserDto;
import com.caldeira.blog.repository.PostRepository;

@Entity
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String name;
	private String lastName;
	private String password;
	private Boolean active;
	private String email;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
	
	public User() { }
	
	public User(UserDto user, PostRepository postRepository) {
		this.setId(user.getId());
		this.setLastName(user.getLastName());
		this.setName(user.getName());
		this.setPassword(user.getPassword());
		this.setUsername(user.getUsername());
		this.setActive(user.getActive());
		this.setEmail(user.getEmail());
		
		List<Post> posts = getPosts(user.getPosts(), postRepository);
		
		if (posts != null) {
			this.setPosts(new ArrayList<Post>());
		} else {
			this.setPosts(posts);
		}
	}
	
	private List<Post> getPosts(List<Long> posts, PostRepository postRepository) {
		
		List<Post> post = new ArrayList<Post>();
		
		if(posts != null) {
			for(int i=0; i < posts.size(); i++) {
				post.add(postRepository.findById(posts.get(i)).get());
			}
			return post;
		}
		return null;
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
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
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
