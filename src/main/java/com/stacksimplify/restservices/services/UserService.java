package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.Entity.User;
import com.stacksimplify.restservices.repositories.UserRepository;
@Service
public class UserService {

	// Autowire the UserRepository
	@Autowired
	private UserRepository userRepository;

	// getAllUsers Method
	public List<User> getAllUsers() {

		return userRepository.findAll();

	}
	//create User
	public User createUser(User user) {
		return userRepository.save(user);
	}
	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	public User updateUserById(Long id, User user)
	{
		user.setUserid(id);
		return userRepository.save(user);
	}
	public void deleteUserById(Long id)
	{
		if (userRepository.findById(id).isPresent())
		{
			userRepository.deleteById(id);
		} 
	}
	public User getUserByUserName(String name)
	{
		return userRepository.findByUsername(name);
	}
}
	// CreateUser Method