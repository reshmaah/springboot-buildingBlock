package com.stacksimplify.restservices.controllers;



import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.Entity.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//Controller -
@Api(tags = "User Management RESTful Services", value = "UserController", description = "Controller for User Management Service")
@RestController
@Validated
@RequestMapping(value="/users")
//@GetMapping(value="/users",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	// getAllUsers Method
	@ApiOperation(value = "Retrieve list of users")
	//@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping
		public List<User> getAllUsers() {
			
			return userservice.getAllUsers();
			
		}
	//CreateUser
	//RequestBody
	//PostMapping
	@PostMapping
	//@PetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder){
		
		try {
		 user= userservice.createUser(user);
		 HttpHeaders header = new HttpHeaders();
		 
		 System.out.println("----------------------------"+user.getUserid());
		 header.setLocation(builder.path("/users/{id}").buildAndExpand(user.getUserid()).toUri());
			return new ResponseEntity<Void>(header, HttpStatus.CREATED);
		}catch(UserExistsException ex)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
		
	}
	//getUserById
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) 
	{
		try {
		return userservice.getUserById(id);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") Long id,@RequestBody User user)
	{
		//try {
		return userservice.updateUserById(id,user);
		/*} catch(UserNotFoundException ex) {
			return new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}*/
	}
	
	@GetMapping("/byusername/{username}")
	public User getUserByUserName(@PathVariable("username") String username) throws UserNameNotFoundException
	{
		User user= userservice.getUserByUserName(username);
		if(user == null) {
			System.out.println("00000000000000000");
			throw new UserNameNotFoundException("USername :"+username +" not found");
			
		}
		return user;
	}
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id)
	{
		userservice.deleteUserById(id);
	}
	
	
}
