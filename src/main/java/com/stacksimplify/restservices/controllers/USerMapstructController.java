package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.Entity.User;
import com.stacksimplify.restservices.dtoa.UserMsDTO;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.rmappers.UserMapper;

@RestController
@RequestMapping( value="/mapstruct/users")
public class USerMapstructController {

	@Autowired 
	UserRepository userrepository;
	
	@Autowired	
	private UserMapper userMapper;
	
	private List<UserMsDTO> usermsDto;
	
	@GetMapping()
	public List<UserMsDTO> getAllUserDTOs() {
		return userMapper.usermsdtos(userrepository.findAll());
	}

	//getUserById
	@GetMapping("/{id}")
	public UserMsDTO getUserById(@PathVariable("id")  Long id) throws UserNotFoundException 
	{
		Optional<User> useroptional = userrepository.findById(id);
		User user = useroptional.get();
		return userMapper.usermsdto(user);
	}
}
