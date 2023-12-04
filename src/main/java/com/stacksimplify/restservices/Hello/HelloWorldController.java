package com.stacksimplify.restservices.Hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
//@RequestMapping("/Hello")
public class HelloWorldController {
	@Autowired
	private ResourceBundleMessageSource messageSource;

	@GetMapping("/helloworld")
	public String helloWorld()
	{
		return "Hello world";
	}
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean()
	{
		return new UserDetails("Reshma", "Astikar","Hyderabad");
	}
	@GetMapping("/hello-int2")
	public String getMessage()
	{
		return messageSource.getMessage("label.hello",null, LocaleContextHolder.getLocale());
	}
	@GetMapping("/hello-int")
	public String getMessagesinI18(@RequestHeader(name="Accept-Language", required=false) String local)
	{
		return messageSource.getMessage("label.hello",null, new Locale(local));
	}
}
