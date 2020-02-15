package com.augusto.restjms.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.augusto.restjms.exception.InvalidDestinationTypeException;

@Service
public class JmsService {

	@Autowired
	JmsTemplate jmsTemplateQueue;
	
	@Autowired
	JmsTemplate jmsTemplateTopic;  /*PubAndSub*/
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	public void sendMessage(String destination, String destinationType, String message) throws InvalidDestinationTypeException {
		try {
			if ("topic".equals(destinationType)) {
				destination = "topic.".concat(destination);
				/*PubAndSub*/
				this.jmsTemplateTopic.convertAndSend(destination, message);
			}
			else if ("queue".equals(destinationType)){
				destination = "queue.".concat(destination);
				this.jmsTemplateQueue.convertAndSend(destination, message);
			}
			else {
				throw new InvalidDestinationTypeException("Invalid destination type. Must be 'topic' or 'queue'"); 
				};
				
			} catch(JmsException e) {
				e.printStackTrace();
			}
		}
	
	/* broadcast [enable PubAndSub on ContainerFactory and JmsTemplate (sender)] */
	
	@JmsListener(containerFactory = "TopicListenerFactory", destination = "topic.financeiro")
	public void messageListener(String msg) {
		log.info("Broadcast Listener I: " + msg);
	}
	
	@JmsListener(containerFactory = "TopicListenerFactory", destination = "topic.financeiro")
	public void messageListener2(String msg) {
		log.info("Broadcast Listener II: " + msg);
	}
	
	/* queue Listener */
	
	@JmsListener(containerFactory = "QueueListenerFactory", destination = "queue.financeiro")
	public void messageListenerQueue(String msg) {
		log.info("Queue Listener: " + msg);
	}
	
}
