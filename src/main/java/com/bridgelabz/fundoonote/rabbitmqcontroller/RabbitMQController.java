package com.bridgelabz.fundoonote.rabbitmqcontroller;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonote.rabbitmq.config.RabbitMQConfig;
import com.bridgelabz.fundoonote.rabbitmq.dto.UserRegistrationRequest;
import com.bridgelabz.fundoonote.user.dto.LoginDto;
import com.bridgelabz.fundoonote.user.dto.RegisterDto;
import com.bridgelabz.fundoonote.user.dto.UserDto;
import com.bridgelabz.fundoonote.user.exception.UserException;
import com.bridgelabz.fundoonote.user.service.IUserService;
import com.bridgelabz.fundoonote.user.utility.UserResponse;

@RestController
@RequestMapping("/home")		
@CrossOrigin("*")
public class RabbitMQController {
	
	@Autowired
	private RabbitTemplate rabbitTemplete;

	public RabbitMQController(RabbitTemplate rabbitTemplete) {this.rabbitTemplete=rabbitTemplete;}
	
	@PostMapping("/user")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRegistrationRequest request) {
		
		ResponseEntity<UserResponse> response = rabbitTemplete.convertAndSend(RabbitMQConfig.registration,RabbitMQConfig.registration,request);
		return response;
	}
	
}