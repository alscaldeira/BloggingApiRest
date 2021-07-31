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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caldeira.blog.controller.dto.UserDto;
import com.caldeira.blog.controller.dto.UserSignDto;
import com.caldeira.blog.model.User;
import com.caldeira.blog.repository.PostRepository;
import com.caldeira.blog.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@GetMapping("/emailvalid")
	public ResponseEntity<User> isEmailValid(@RequestParam String email){
		return userRepository.findByEmail(email).isPresent() ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<UserDto> signUp(@RequestBody UserSignDto userDto) {
		
		Optional<User> u = userRepository.findByEmail(userDto.getEmail());
		
		if(!u.isPresent()) {
			User user = new User(userDto);
			user = userRepository.save(new User(userDto));
			return ResponseEntity.ok(new UserDto(user));
		} else {
			u.get().setActive(true);
			User user = userRepository.save(u.get());
			return ResponseEntity.ok(new UserDto(user));
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getOne(@PathVariable Long id) {
		UserDto userDto = new UserDto(userRepository.findById(id).get());
		return !userDto.equals(null) || userDto != null ? ResponseEntity.ok(userDto) : ResponseEntity.badRequest().build(); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent() || user.get().equals(null)) {
			return ResponseEntity.notFound().build();
		} else if(user.get().getActive() == false) { 
			return ResponseEntity.notFound().build();
		}
		
		user.get().setActive(false);
		userRepository.save(user.get());
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> update(@RequestBody UserDto userDto, @PathVariable Long id) {
		UserDto oldUserDto = new UserDto(userRepository.findById(id).get());
		
		if(oldUserDto.equals(null) || oldUserDto == null) {
			return ResponseEntity.notFound().build();
		}
		
		userDto.setId(oldUserDto.getId());
		User user = new User();
		user = new User(userDto, postRepository);
		
		UserDto userToReturn = new UserDto(userRepository.save(user));
		return ResponseEntity.ok(userToReturn);
	}
}
