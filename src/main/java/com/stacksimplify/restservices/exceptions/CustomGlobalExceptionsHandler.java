package com.stacksimplify.restservices.exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionsHandler extends ResponseEntityExceptionHandler {
	// MethodArguementNotvalidaException.
	/**
	 * Customize the response for MethodArgumentNotValidException.
	 * <p>
	 * This method delegates to {@link #handleExceptionInternal}.
	 * 
	 * @param ex      the exception
	 * @param headers the headers to be written to the response
	 * @param status  the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustumErrorDetailsException custumerrordetails = new CustumErrorDetailsException(new Date(),
				"FromMethodArgumentNotvalud exception in Global exception handler", ex.getLocalizedMessage());
		return new ResponseEntity<>(custumerrordetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustumErrorDetailsException custumerrordetails = new CustumErrorDetailsException(new Date(),
				"handleHttpRequestMethodNotSupported exception in Global exception handler - MethodNotallowed",
				ex.getMessage());
		return new ResponseEntity<>(custumerrordetails, HttpStatus.METHOD_NOT_ALLOWED);

	}
	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest req) {
		CustumErrorDetailsException custumerrordetails = new CustumErrorDetailsException(new Date(),
				ex.getMessage(),
				req.getDescription(true));
		System.out.println("HELLLLLLLLL");
		return new ResponseEntity<>(custumerrordetails, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(ConstraintViolationException.class)
	//ConstraintViolationException
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest req)
	{
		CustumErrorDetailsException custumerrordetails = new CustumErrorDetailsException(new Date(),
				ex.getMessage(),
				req.getDescription(true));
		System.out.println("handleConstraintViolationException");
		return new ResponseEntity<>(custumerrordetails, HttpStatus.BAD_REQUEST);
	}
	
}
