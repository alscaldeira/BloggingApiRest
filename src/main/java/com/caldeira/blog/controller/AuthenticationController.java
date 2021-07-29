package com.caldeira.blog.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caldeira.blog.config.security.TokenService;
import com.caldeira.blog.controller.dto.TokenDto;
import com.caldeira.blog.controller.dto.UserDto;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody UserDto userDto) {
		UsernamePasswordAuthenticationToken loginData = userDto.convert();
		
		Authentication authentication = authManager.authenticate(loginData);
		String token = tokenService.generateToken(authentication);
		
		return ResponseEntity.ok(new TokenDto(token, "Bearer "));
	}
	
}