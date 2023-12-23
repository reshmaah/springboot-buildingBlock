package com.stacksimplify.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.Entity.User;
import com.stacksimplify.restservices.dtoa.UserMmDTO;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value="/modelmapper/users")
public class UserModelMapperController {
	@Autowired
	UserService userservice;
	@Autowired
	private ModelMapper modelmapper;
	//getUserById
		@GetMapping("/{id}")
		public UserMmDTO getUserById(@PathVariable("id") @Min(1) Long id) 
		{
			try {
			Optional<User> useroptional =  userservice.getUserById(id);
			if (!useroptional.isPresent()) {
				throw new UserNotFoundException("USernotFound error");
			}
			User user = useroptional.get();
			UserMmDTO userdto = modelmapper.map(user, UserMmDTO.class);
			return userdto;
			} catch(UserNotFoundException ex) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			}
		}
}
