package com.augusto.restjms.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsService {

	@Autowired
	JmsTemplate jmsTemplateQueue;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	public void sendMessage(String queue, String message) {
		try {
			jmsTemplateQueue.convertAndSend(queue, message);
		} catch(JmsException e) {
			e.printStackTrace();
		}
	}
	
	@JmsListener(containerFactory = "getListenerFactory", destination = "queue.financeiro")
	public void messageListener(String msg) {
		log.info("Received new message: " + msg);
	}
}
