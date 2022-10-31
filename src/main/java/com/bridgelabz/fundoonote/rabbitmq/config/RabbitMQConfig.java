package com.bridgelabz.fundoonote.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RabbitMQConfig {
   
	private CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
	
	public RabbitMQConfig(CachingConnectionFactory cachingConnectionFactory) {
		this.cachingConnectionFactory=cachingConnectionFactory;
	}
	
	@Bean
	public Queue createUserRegistrationQueue() {
		return new Queue("registration");
	}
	
	@Bean
	public TopicExchange exchange () {
		return new TopicExchange("registration");
	}
	
	public Binding binding(Queue createUserRegistrationQueue,TopicExchange exchange) {
		return BindingBuilder.bind(createUserRegistrationQueue).to(exchange).with("registration");		
	}
	
	@Bean  
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public RabbitTemplate rabbitTemplete(Jackson2JsonMessageConverter converter) {
		RabbitTemplate rabbitTemplete =new RabbitTemplate(cachingConnectionFactory);
		rabbitTemplete.setMessageConverter(converter());
		return rabbitTemplete;
	}
}
