package com.ama.askme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ama.askme.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUserId(Long userId);

}
