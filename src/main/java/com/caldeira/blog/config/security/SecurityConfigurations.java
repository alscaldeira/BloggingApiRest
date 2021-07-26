package com.caldeira.blog.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	// Authentication configurations
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	}
	
	// Authorization configurations
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/category/**").permitAll()
			.antMatchers(HttpMethod.GET, "/user/**").permitAll()
			.antMatchers(HttpMethod.POST, "/user/login").permitAll()
			.antMatchers(HttpMethod.GET, "/post/**").permitAll()
			.and()
			.csrf().disable();
	}
	
	// Static resources configurer (js, css, img, etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
	}
	
}
