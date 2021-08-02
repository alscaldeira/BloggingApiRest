package com.caldeira.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caldeira.blog.config.security.TokenService;
import com.caldeira.blog.controller.dto.LoginDto;
import com.caldeira.blog.controller.dto.SignUpMessageDto;
import com.caldeira.blog.controller.dto.TokenDto;
import com.caldeira.blog.controller.dto.UserDto;
import com.caldeira.blog.model.User;
import com.caldeira.blog.repository.PostRepository;
import com.caldeira.blog.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	PostRepository postRepository;
	
	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody LoginDto user) {
		
		UsernamePasswordAuthenticationToken loginData = user.convert();

		Authentication authentication = authManager.authenticate(loginData);
		
		String token = tokenService.generateToken(authentication);
		
		return ResponseEntity.ok(new TokenDto(token, "Bearer "));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
		
		if(userRepository.findByUsername(userDto.getUsername()).isPresent()) {
				return ResponseEntity.badRequest().body(new SignUpMessageDto("Username already in use"));
		}
		
		if(userRepository.findByEmail(userDto.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().body(new SignUpMessageDto("Email already in use"));
		}
		
		User user = userRepository.save(new User(userDto, postRepository));
		
		return ResponseEntity.ok(new UserDto(user));
	}
}
