package com.cuemby.attendance.controllers.v1.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cuemby.attendance.services.exception.ResourceAlreadyExistsException;
import com.cuemby.attendance.services.exception.ResourceNotFoundException;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<Object> handleNotfoundException(Exception exception,WebRequest request ){
		
		return new ResponseEntity<Object>( exception.getMessage() != null ?  exception.getMessage():"Resource Not Found",
									new HttpHeaders(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ResourceAlreadyExistsException.class})
	public ResponseEntity<Object> handleResourceAlreadyExistsException(Exception exception,WebRequest request ){
		
		return new ResponseEntity<Object>( exception.getMessage() != null ?  exception.getMessage():"Resource Aready Exist",
									new HttpHeaders(),HttpStatus.CONFLICT);
	}
	
//	@ExceptionHandler({MethodArgumentNotValidException.class})
//	public ResponseEntity<Object> handleMethodArgumentNotValidException(Exception exception,WebRequest request ){
//		
//		return new ResponseEntity<Object>( exception.getMessage() != null ?  exception.getMessage():"Field error in object 'employeeDTO",
//									new HttpHeaders(),HttpStatus.BAD_REQUEST);
//	}
	
	

}
