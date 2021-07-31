package com.caldeira.blog.controller;

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

import com.caldeira.blog.controller.dto.CategoryDto;
import com.caldeira.blog.model.Category;
import com.caldeira.blog.repository.CategoryRepository;
import com.caldeira.blog.repository.PostRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@PostMapping
	public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto) { 
		Category category = new Category(categoryDto, postRepository, categoryRepository);
		
		categoryDto = new CategoryDto(categoryRepository.save(category));
		
		return ResponseEntity.ok(categoryDto);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<CategoryDto> getByName(@PathVariable String name) {
		Optional<Category> category = categoryRepository.findByName(name);
		
		if(!category.isPresent() || category.get() == null)
			return ResponseEntity.notFound().build();
		
		CategoryDto categoryDto = new CategoryDto(category.get());
		
		return ResponseEntity.ok(categoryDto);
	}
	
	@PutMapping("/{name}")
	public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryDto, @PathVariable String name) { 
		Optional<Category> category = categoryRepository.findByName(name);
		
		if(!category.isPresent() || category.get().equals(null))
			return ResponseEntity.notFound().build();
		
		category.get().setName(categoryDto.getName());

		categoryDto = new CategoryDto(categoryRepository.save(category.get()));
		
		return ResponseEntity.ok(categoryDto);
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<CategoryDto> delete(@PathVariable String name) {
		try {
			categoryRepository.deleteByName(name);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
