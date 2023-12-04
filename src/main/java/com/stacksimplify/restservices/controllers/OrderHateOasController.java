package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.Entity.Order;
import com.stacksimplify.restservices.Entity.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class OrderHateOasController {
	@Autowired
	private UserRepository userrepository;
	@Autowired
	private OrderRepository orderrepository;
	@GetMapping("/{userid}/orders")
	public Resources<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		Optional<User> user = userrepository.findById(userid);
		if (user == null)
			throw new UserNotFoundException("User not found");

		List<Order>orders = user.get().getOrders();
		Resources<Order> finalorders = new Resources<Order>(orders);
		return finalorders;
	}
}
