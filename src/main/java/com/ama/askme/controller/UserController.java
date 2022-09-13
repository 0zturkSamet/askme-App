package com.ama.askme.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ama.askme.entity.User;
import com.ama.askme.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	
	private UserService userService;
	
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> getAllUsers(){
		return userService.findAllUsers();
	}
	
	@PostMapping
	public User createUser(@RequestBody User newUser) {
		return userService.createNewUSer(newUser);
		
	}
	
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable Long userId) {
		//custom exception later on
		return userService.getOneUserById(userId);
	}
	
	@PutMapping("/{userId}")
	public User updateUserById(@PathVariable Long userId,@RequestBody User newUser) {
		return userService.updateUserById(userId, newUser);
	}
	@DeleteMapping("/{userId}")
	public void deleteUserById(@PathVariable Long userId) {
		userService.deleteById(userId);
		
	}
	
	
}
