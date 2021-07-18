package com.caldeira.blog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caldeira.blog.controller.dto.PostDto;
import com.caldeira.blog.model.Post;
import com.caldeira.blog.repository.CategoryRepository;
import com.caldeira.blog.repository.PostRepository;
import com.caldeira.blog.repository.UserRepository;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping
	public ResponseEntity<List<PostDto>> getAll() {
		List<PostDto> postDto = new ArrayList<PostDto>();
		
		List<Post> posts = postRepository.findAll();
		
		if(posts.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		if(posts != null) {
			if(!posts.isEmpty()) {
				for(int i=0; i < posts.size(); i++) {
					if(posts.get(i).getUser().getActive()) {
						postDto.add(new PostDto(posts.get(i)));
					}
				}
			}
		}
		return ResponseEntity.ok(postDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getOne(@PathVariable Long id) {
		Optional<Post> post = postRepository.findById(id);
		
		if((post.get().equals(null) || !post.isPresent()) || !post.get().getUser().getActive()) {
			return ResponseEntity.notFound().build();
		}
		
		PostDto postDto = new PostDto(post.get());
		
		if(post.get().getViews() == null) {
			post.get().setViews(0L);
		} else {
			post.get().setViews(post.get().getViews() + 1);
		}
		
		postRepository.save(post.get());
		
		return ResponseEntity.ok(postDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> update(@PathVariable Long id, @RequestBody PostDto postDto) {
		
		Post post = postRepository.findById(id).get();
		
		if(post == null || post.equals(null)) {
			return ResponseEntity.notFound().build();
		}
		
		post = new Post(postDto, userRepository, categoryRepository); 
		post.setId(id);
		post = postRepository.save(post);
		postDto = new PostDto(post);
		return ResponseEntity.ok(postDto);
		
	}
	
	@PostMapping
	public ResponseEntity<PostDto> create(@RequestBody PostDto postDto) {
		Post post = new Post(postDto, userRepository, categoryRepository);
		post.setViews(0L);
		postDto = new PostDto(postRepository.save(post));
		return ResponseEntity.ok(postDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			postRepository.deleteById(id);
		} catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
				
		return ResponseEntity.ok().build();
	}
}
