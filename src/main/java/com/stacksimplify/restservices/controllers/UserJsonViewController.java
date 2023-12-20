
package com.stacksimplify.restservices.controllers;

import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservices.Entity.User;
import com.stacksimplify.restservices.Entity.Views;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value = "/jsonview/users")

public class UserJsonViewController {

	@Autowired
	private UserService userservice;

	// getUserById
	@GetMapping("/external/{id}")
	@JsonView(Views.External.class)
	public Optional<User> getUserByIdExternal(@PathVariable("id") @Min(1) Long id) {
		try {
		return userservice.getUserById(id);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	// getUserById
	@JsonView(Views.Internal.class)
		@GetMapping("/internal/{id}")
		public Optional<User> getUserByIdInternal(@PathVariable("id") @Min(1) Long id,@RequestParam Set<String> fields) {
		try {
		return userservice.getUserById(id);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		}
}
