package com.stacksimplify.restservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {
	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustumErrorDetailsException usernameNotFound(UserNameNotFoundException ex)
	{
		return new CustumErrorDetailsException(new Date(), "From @RestControllerAdvice", ex.getMessage());
	}
}
