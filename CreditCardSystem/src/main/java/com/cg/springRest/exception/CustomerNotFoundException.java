package com.cg.springRest.exception;

@SuppressWarnings("serial")
public class CustomerNotFoundException extends RuntimeException{

	public CustomerNotFoundException() {
	
	}

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
