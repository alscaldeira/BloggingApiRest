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
		Category category = new Category(categoryDto, postRepository);
		category.setId(null);
		categoryDto = new CategoryDto(categoryRepository.save(category));
		
		return ResponseEntity.ok(categoryDto);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAll() {
		List<Category> category = new ArrayList();
		category = categoryRepository.findAll();
		
		if(category.isEmpty())
			return ResponseEntity.ok().build();

		List<CategoryDto> categoriesDto = new ArrayList<CategoryDto>();
		for(int i=0; i < category.size(); i++) {
			categoriesDto.add(new CategoryDto(category.get(i)));
		}
		
		return ResponseEntity.ok(categoriesDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getOne(@PathVariable Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		
		if(category.get().equals(null) || !category.isPresent()) 
			return ResponseEntity.notFound().build();
			
		CategoryDto categoryDto = new CategoryDto(category.get());
		
		return ResponseEntity.ok(categoryDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryDto, @PathVariable Long id) { 
		Category category = new Category(categoryDto, postRepository);
		category.setId(id);
		categoryDto = new CategoryDto(categoryRepository.save(category));
		
		return ResponseEntity.ok(categoryDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CategoryDto> delete(@PathVariable Long id) {
		try {
			categoryRepository.deleteById(id);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
