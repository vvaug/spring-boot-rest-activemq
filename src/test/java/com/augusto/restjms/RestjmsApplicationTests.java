package com.augusto.restjms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

@SpringBootTest
class RestjmsApplicationTests {

	@Autowired
	JmsTemplate jmsTemplateTopic;
	@Autowired
	JmsTemplate jmsTemplateQueue;
	
	@Test
	void contextLoads() {
		this.send("queue.financeiro", "Nova Mensagem de Spring!");
		
	}
	
	public void send(String queueName, String message) {
		jmsTemplateQueue.convertAndSend(queueName, message);
	}
	
	@JmsListener(destination = "queue.financeiro", containerFactory = "getListenerFactory")
	public void receiveMessage(String message) {
		System.out.println(message);
	}
}
