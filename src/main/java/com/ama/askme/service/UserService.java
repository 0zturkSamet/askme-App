package com.ama.askme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ama.askme.entity.User;
import com.ama.askme.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	

	//get All Users
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}


	//create new user
	public User createNewUSer(User newUser) {
		return userRepository.save(newUser);
	}


	//find user by id
	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}
	
	//update user by id
	public User updateUserById(Long userId,User newUser) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User foundUser = user.get();
			foundUser.setUserName(newUser.getUserName());
			foundUser.setPassword(newUser.getPassword());
			userRepository.save(foundUser);
			return foundUser;
		}else
			return null;
	}


	//delete user by id
	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
		
	}

	
	
	

}
