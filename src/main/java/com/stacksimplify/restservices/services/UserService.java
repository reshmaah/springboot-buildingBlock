package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.Entity.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
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
	public User createUser(User user) throws UserExistsException {
		User userexists = userRepository.findByUsername(user.getUsername());
		if(userexists !=null) {
			throw new UserExistsException("Useralready exists");
		}
		return userRepository.save(user);
	}
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found exception in repository");
		}
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