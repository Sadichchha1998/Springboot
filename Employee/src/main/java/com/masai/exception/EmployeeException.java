package com.masai.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class EmployeeException extends RuntimeException {

	public EmployeeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
