package com.spring.exceptions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Employee Not Found")
public class EmployeeNotFoundException extends Exception {
	
	private static final long serialVersionUID = -3332292346834265371L;

	public EmployeeNotFoundException(int id) {
		super("EmployeeNotFoundException with id="+id);
	}
	
}
