package com.ama.askme.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ama.askme.entity.Post;
import com.ama.askme.entity.User;
import com.ama.askme.repository.PostRepository;
import com.ama.askme.request.PostCreateRequest;
import com.ama.askme.request.PostUpdateRequest;

@Service
public class PostService {
	
	private PostRepository postRepository;
	private UserService userService;

	public PostService(PostRepository postRepository,UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}
	
	//get All posts
	public List<Post> getAllPosts(Optional<Long> userId) {
		if(userId.isPresent()) {
			return postRepository.findByUserId(userId.get());
		}
		return postRepository.findAll();
	}

	// get one post
	public Post getOnePostById(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}

	public Post createOnePost(PostCreateRequest newPostRequest) {
		User user = userService.getOneUserById(newPostRequest.getUserId());
		if(user == null)
			return null;
		Post toSave = new Post();
		toSave.setId(newPostRequest.getId());
		toSave.setText(newPostRequest.getText());
		toSave.setTitle(newPostRequest.getTitle());
		toSave.setUser(user);
		return postRepository.save(toSave);
	}

	public Post updateOnePostById(Long postId,PostUpdateRequest updatePost) {
		Optional<Post> post = postRepository.findById(postId);
		if(post.isPresent()) {
			Post toUpdate = post.get();
			toUpdate.setText(updatePost.getText());
			toUpdate.setTitle(updatePost.getTitle());
			postRepository.save(toUpdate);
			return toUpdate;
		}
		return null;
	}

	public void deleteOnePostById(Long postId) {
		postRepository.deleteById(postId);
		
	}
	
	
	
	
	
	
	
	
	
}
