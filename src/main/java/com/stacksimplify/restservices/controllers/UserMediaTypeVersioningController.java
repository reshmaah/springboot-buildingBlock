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
import com.stacksimplify.restservices.dtoa.UserDTOV1;
import com.stacksimplify.restservices.dtoa.UserDTOV2;
import com.stacksimplify.restservices.dtoa.UserMmDTO;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value="/versioning/mediatype/users")
public class UserMediaTypeVersioningController {
	@Autowired
	UserService userservice;
	@Autowired
	private ModelMapper modelmapper;
	//getUserById
	// CustumheaderBased versioning V1
		@RequestMapping(value="{id}", produces = "application/vnd.stacksimplify.app-v1+json")
		public UserDTOV1 getUserById(@PathVariable("id") @Min(1) Long id) 
		{
			try {
			Optional<User> useroptional =  userservice.getUserById(id);
			if (!useroptional.isPresent()) {
				throw new UserNotFoundException("USernotFound error");
			}
			User user = useroptional.get();
			UserDTOV1 userdto = modelmapper.map(user, UserDTOV1.class);
			return userdto;
			} catch(UserNotFoundException ex) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			}
		}
		//V1 version
		@RequestMapping(value="{id}", produces = "application/vnd.stacksimplify.app-v2+json")
				public UserDTOV2 getUserById2(@PathVariable("id") @Min(1) Long id) 
				{
					try {
					Optional<User> useroptional =  userservice.getUserById(id);
					if (!useroptional.isPresent()) {
						throw new UserNotFoundException("USernotFound error");
					}
					User user = useroptional.get();
					UserDTOV2 userdto = modelmapper.map(user, UserDTOV2.class);
					return userdto;
					} catch(UserNotFoundException ex) {
						throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
					}
				}
				
}
