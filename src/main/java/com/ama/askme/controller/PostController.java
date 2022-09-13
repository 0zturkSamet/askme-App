package com.ama.askme.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ama.askme.entity.Post;
import com.ama.askme.request.PostCreateRequest;
import com.ama.askme.request.PostUpdateRequest;
import com.ama.askme.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

     private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
     
	

	@GetMapping
	public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
		
		return postService.getAllPosts(userId);
		
	}
	
	@GetMapping("/{postId}")
	public Post getOnePost(@PathVariable Long postId) {
		return postService.getOnePostById(postId);
		
	}
	
	//create new post
	@PostMapping
	public Post createOnePost(@RequestBody PostCreateRequest newPostRequest) {
		return postService.createOnePost(newPostRequest);
	}
	@PutMapping("/{postId}")
	public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost) {
		return postService.updateOnePostById(postId,updatePost);
		
	}
	
	@DeleteMapping("/{postId}")
	public void deleteOnePostById(@PathVariable Long postId) {
		postService.deleteOnePostById(postId);
	}
	
	
     
	
}
