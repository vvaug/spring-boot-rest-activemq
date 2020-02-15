package com.augusto.restjms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidDestinationTypeException.class)
	public ResponseEntity<?> handleInvalidDestinationTypeException(InvalidDestinationTypeException ex){
		return ResponseEntity.badRequest().build();
	}
}
