package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.Entity.User;
import com.stacksimplify.restservices.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//Controller -
@Api(tags = "User Management RESTful Services", value = "UserController", description = "Controller for User Management Service")
@RestController
@Validated

//@GetMapping(value="/users",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	// getAllUsers Method
	@ApiOperation(value = "Retrieve list of users")
	//@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/users")
		public List<User> getAllUsers() {
			
			return userservice.getAllUsers();
			
		}
	//CreateUser
	//RequestBody
	//PostMapping
	@PostMapping("/users")
	//@PetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public User createUser(@RequestBody User user) {
		
		return userservice.createUser(user);
	}
	//getUserById
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id)
	{
		return userservice.getUserById(id);
	}
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id,@RequestBody User user)
	{
		
		user.setUserid(id);
		return userservice.updateUserById(id,user);
	}
	
	@GetMapping("/users/byusername/{username}")
	public User getUserByUserName(@PathVariable("username") String username)
	{
		return userservice.getUserByUserName(username);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id") Long id)
	{
		userservice.deleteUserById(id);
	}
	
	
}
