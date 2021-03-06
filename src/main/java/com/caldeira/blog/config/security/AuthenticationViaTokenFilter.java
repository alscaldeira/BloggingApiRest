package com.caldeira.blog.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.caldeira.blog.model.User;
import com.caldeira.blog.repository.UserRepository;

public class AuthenticationViaTokenFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	
	private UserRepository userRepository;
	
	public AuthenticationViaTokenFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		
		boolean isValid = tokenService.isTokenValid(token);

		if(isValid) {
			authenticateUser(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void authenticateUser(String token) {
		Long idUser = tokenService.getIdUsuario(token);
		User user = userRepository.findById(idUser).get();

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, 
																						   user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
