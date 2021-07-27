package com.caldeira.blog.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.caldeira.blog.model.User;
import com.caldeira.blog.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findActiveUserByUsername(username);
		
		System.out.println(user.toString());
		
		if (user.isPresent()) {			
			return user.get();
		}
		
		throw new UsernameNotFoundException("Invalid data:" + username);
	}
	
}
