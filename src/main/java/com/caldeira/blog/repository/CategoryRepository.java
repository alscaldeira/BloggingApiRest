package com.caldeira.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.caldeira.blog.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM Category c where name = ?1 limit 1")
	public Optional<Category> findByName(String name);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Category c where c.name = ?1")
	public void deleteByName(String name);
	
}
