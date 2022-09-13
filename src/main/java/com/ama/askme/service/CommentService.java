package com.ama.askme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ama.askme.entity.Comment;
import com.ama.askme.entity.Post;
import com.ama.askme.entity.User;
import com.ama.askme.repository.CommentRepository;
import com.ama.askme.request.CommentCreateRequest;
import com.ama.askme.request.CommentUpdateRequest;

@Service
public class CommentService {

	
	private CommentRepository commentRepository;
	private UserService userService;
	private PostService postService;

	public CommentService(CommentRepository commentRepository,UserService userService,PostService postService) {
		this.commentRepository = commentRepository;
		this.userService=userService;
		this.postService=postService;
	}

	public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
		if(userId.isPresent() && postId.isPresent()) {
			commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
		}else if(userId.isPresent()) {
			commentRepository.findByUserId(userId.get());
		}else if(postId.isPresent()) {
			commentRepository.findByPostId(postId.get());
		}else
			return commentRepository.findAll();
		
		return null;
	}

	public Comment getOneCommentById(Long commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}

	public Comment createOneComment(CommentCreateRequest request) {
		User user = userService.getOneUserById(request.getUserId());
		Post post = postService.getOnePostById(request.getPostId());
		if(user !=null && post !=null) {
			Comment commentToSave = new Comment();
			commentToSave.setId(request.getId());
			commentToSave.setUser(user);
			commentToSave.setPost(post);
			commentToSave.setText(request.getText());
			return commentRepository.save(commentToSave);
		}else
		
		return null;
	}

	public Comment updateOneCommentById(Long commentId, CommentUpdateRequest request) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if(comment.isPresent()) {
			Comment commentToUpdate = comment.get();
			commentToUpdate.setText(request.getText());
			return commentRepository.save(commentToUpdate);
		}
		return null;
		
	}

	public void deleteCommentById(Long commentId) {
		commentRepository.deleteById(commentId);
		
	}
	
	
	
	
}
