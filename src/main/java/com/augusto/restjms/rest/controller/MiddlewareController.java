package com.augusto.restjms.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.augusto.restjms.rest.service.JmsService;

/*
 * Author: Victor Silva
 */

@Controller
@RequestMapping("/jms")
public class MiddlewareController {
	
	@Autowired
	JmsService jmsService;
	
	@PostMapping("financeiro/send")
	public ResponseEntity<?> sendMessageToQueue(@RequestBody String message){
		jmsService.sendMessage("queue.financeiro", message);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
