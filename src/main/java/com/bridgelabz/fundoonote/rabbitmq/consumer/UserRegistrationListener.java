package com.bridgelabz.fundoonote.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserRegistrationListener {
	 @RabbitListener(queues = {"q.user-registration"})
	    public <UserRegistrationRequest> void onUserRegistration(UserRegistrationRequest event) {
	            log.info("User Registration Event Received: {}", event);
	    }
	 
	 @RabbitListener(queues = {"q.user-registration"})
	 public void onUserRegistration(UserRegistrationListener event) {

	         log.info("User Registration Event Received: {}", event);

	         /*
	         Relevant business logic for user registration
	         */

	         emailService.sendEmail(event.getEmail());
	         smsService.sendSms(event.getMobileNumber());

	 }
	
}
