package com.augusto.restjms.jms.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JmsConfig {

	@Value("${spring.activemq.broker-url}")
	private String brokerURL;
	
	@Bean
	public ActiveMQConnectionFactory ConnectionFactory() {
		return new ActiveMQConnectionFactory(brokerURL);
	}
	
	@Bean
	public JmsListenerContainerFactory<?> getListenerFactory(ConnectionFactory connectionFactory,
			                                                 DefaultJmsListenerContainerFactoryConfigurer configurer){
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		/* for topic */
		//factory.setPubSubDomain(true);
		return factory;
	}
	
	@Bean
	public JmsTemplate jmsTemplateQueue() {
		JmsTemplate jmsTemplate = new JmsTemplate(this.ConnectionFactory());
		return jmsTemplate;
	}
	
	@Bean
	public JmsTemplate jmsTemplateTopic() {
		JmsTemplate jmsTemplate = new JmsTemplate(this.ConnectionFactory());
		jmsTemplate.setPubSubDomain(true);
		return jmsTemplate;
	}
	
}
