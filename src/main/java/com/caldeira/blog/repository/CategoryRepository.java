package com.caldeira.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caldeira.blog.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
