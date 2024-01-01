package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.Entity.Order;
import com.stacksimplify.restservices.Entity.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class OrderController {
	@Autowired
	private UserRepository userrepository;
	@Autowired
	private OrderRepository orderrepository;
	
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		Optional<User> user = userrepository.findById(userid);
		if (user == null)
			throw new UserNotFoundException("User not found");

		return user.get().getOrders();
	}
	@PostMapping("/{userid}/orders")
	public void createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException
	{
		Optional<User> useroptional = userrepository.findById(userid);
		if (useroptional == null)
			throw new UserNotFoundException("User not found");
		User user = useroptional.get();
		order.setUser(user);
		orderrepository.save(order); 
	}
	@GetMapping("/{userid}/orders/{orderid}")
	public Order getOrderByOrderId(@PathVariable Long userid, @PathVariable Long orderid) throws UserNotFoundException
	{
		Optional<User> useroptional = userrepository.findById(userid);
		if (useroptional == null)
			throw new UserNotFoundException("User not found");
		Optional<Order> orderoptional = orderrepository.findById(orderid);
		if (orderoptional == null)
			throw new UserNotFoundException("Order not found");
		return orderoptional.get();
	}
}
