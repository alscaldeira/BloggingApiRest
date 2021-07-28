package com.caldeira.blog.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.caldeira.blog.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${blogging.jwt.expiration}")
	private String expiration;
	
	@Value("${blogging.jwt.secret}")
	private String secret;
	
	public String generateToken(Authentication authentication) {
		
		
		User loggedin = (User) authentication.getPrincipal();
		Date today = new Date();
		Date expirationTime = new Date(today.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("BLOGGING API REST ;)")
				.setSubject(loggedin.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(expirationTime)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		return Long.parseLong(Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject());
	}
	
}
