package com.stacksimplify.restservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.data.UserDetails;

//Controller
@RestController
public class HelloWorldController {
	
	@GetMapping("/helloWorld")
	public String helloWorld()
	{
		return "Hello World1";
	}
	@GetMapping("/helloword_bean")
	public UserDetails hellodetailsBean()
	{
		return new UserDetails("Reshma", "Astiker", "Hyd");
	}

}
