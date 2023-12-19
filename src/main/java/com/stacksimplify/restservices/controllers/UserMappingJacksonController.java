package com.stacksimplify.restservices.controllers;

import java.util.HashSet;
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

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservices.Entity.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value = "/jacksonfiler/users")
public class UserMappingJacksonController {
	@Autowired
	private UserService userservice;

	// getUserById
	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userP = userservice.getUserById(id);
			User user = userP.get();
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			Set<String> fields = new HashSet<String>();
			fields.add("userid");
			fields.add("username");
			fields.add("ssn");
			FilterProvider filter = new SimpleFilterProvider().addFilter("UserFiler",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			mapper.setFilters(filter);
			return mapper;
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	// getUserById
		@GetMapping("/params/{id}")
		public MappingJacksonValue getUserByIdParams(@PathVariable("id") @Min(1) Long id,@RequestParam Set<String> fields) {
			try {
				Optional<User> userP = userservice.getUserById(id);
				User user = userP.get();
				MappingJacksonValue mapper = new MappingJacksonValue(user);
				/*
				 * Set<String> fields = new HashSet<String>(); fields.add("userid");
				 * fields.add("username"); fields.add("ssn");
				 */
				FilterProvider filter = new SimpleFilterProvider().addFilter("UserFiler",
						SimpleBeanPropertyFilter.filterOutAllExcept(fields));
				mapper.setFilters(filter);
				return mapper;
			} catch (UserNotFoundException ex) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			}
		}
}
