package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.Entity.Order;
import com.stacksimplify.restservices.Entity.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class UserHateoasController {
	//@Autowired
	//private UserRepository userrepository;
	
	/*
	 * @Autowired private OrderRepository orderrepository;
	 */
	
	@Autowired
	private UserService userservice;
	
//	@GetMapping
//	public List<User> getAllUsers() {
//		
//		return userservice.getAllUsers();
//		
//	}

	// getUserById
	@GetMapping("")
	public Resources<User> getAllUsers() throws UserNotFoundException {
		List<User> users =  userservice.getAllUsers();
		for (User user: users) {
			Long userid = user.getUserid();
			Link selflink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selflink);
			Resources<Order> orders = ControllerLinkBuilder.methodOn(OrderHateOasController.class).getAllOrders(userid);
			Link orderLink = ControllerLinkBuilder.linkTo(orders).withSelfRel();
			user.add(orderLink);
		}
		Resources<User> finalUserList = new Resources<User>(users);
		
		return finalUserList;
	}

	@GetMapping("/{id}")
	public Resource<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			System.out.println("Service ---------" + id);
			Optional<User> useroptional =  userservice.getUserById(id);
			User user = useroptional.get();
			Long userid = user.getUserid();
			Link selflink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selflink);
			Resource<User> finalresource = new Resource<User>(user);
			return finalresource;
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
 
}
