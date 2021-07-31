package com.caldeira.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.caldeira.blog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	@Query(nativeQuery =  true, value = "select * from Post p where p.views > 1 order by p.views limit 12")
	public Optional<List<Post>> findTheMoreViewed();
	
}
