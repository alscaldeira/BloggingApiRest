package com.caldeira.blog.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.caldeira.blog.controller.dto.UserDto;
import com.caldeira.blog.controller.dto.UserSignDto;
import com.caldeira.blog.repository.UserRepository;

@Entity
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String name;
	private String lastName;
	private String password;
	private Boolean active;
	private String email;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Profile> profiles = new ArrayList<Profile>(); 

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Post> posts;
	
	public User() { }

	public User(UserSignDto userDto) {
		this.setLastName(userDto.getLastName());
		this.setName(userDto.getName());
		this.setPassword(userDto.getPassword());
		this.setUsername(userDto.getUsername());
		this.setActive(true);
		this.setEmail(userDto.getEmail());
	}
	
	public User(UserDto userDto, UserRepository userRepository) {
		Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
		
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			this.setId(user.getId());
			this.setLastName(user.getLastName());
			this.setName(user.getName());
			this.setPassword(user.getPassword());
			this.setUsername(user.getUsername());
			this.setEmail(user.getEmail());
			this.setPosts(user.getPosts());
			this.setActive(user.getActive());
			this.profiles = new ArrayList<Profile>();
			this.profiles.add(new Profile("ADM"));
			
			// IF USER IS NOT PRESENT, IT'S A NEW USER
		} else {
			this.setLastName(userDto.getLastName());
			this.setName(userDto.getName());
			this.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
			this.setUsername(userDto.getUsername());
			this.setEmail(userDto.getEmail());
			this.setActive(true);
			this.profiles = new ArrayList<Profile>();
			this.profiles.add(new Profile("ADM"));
		}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", lastName=" + lastName + ", password="
				+ password + ", active=" + active + ", email=" + email + ", posts=" + posts + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.profiles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.active;
	}

	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(username, password);
	}
}
