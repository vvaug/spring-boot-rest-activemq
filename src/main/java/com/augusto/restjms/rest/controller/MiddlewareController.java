package com.augusto.restjms.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.augusto.restjms.exception.InvalidDestinationTypeException;
import com.augusto.restjms.rest.service.JmsService;

/*
 * Author: Victor Silva
 */

@Controller
@RequestMapping("/jms")
public class MiddlewareController {
	
	@Autowired
	JmsService jmsService;
	
	@PostMapping("/send")
	public ResponseEntity<?> sendMessageToQueue(@RequestParam("destination") String destination,
			                                    @RequestParam("type") String destinationType,
			                                    @RequestBody String message) throws InvalidDestinationTypeException{
		jmsService.sendMessage(destination, destinationType, message);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
}
