package com.caldeira.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.caldeira.blog.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "select u from User u where u.active = true")
	public List<User> findAllActiveUsers();
	
	@Query(value = "select u from User u where u.active = true and username = ?1")
	public Optional<User> findActiveUserByUsername(String name);

	public Optional<User> findByEmail(String email);
	
}
