package com.bridgelabz.fundoonote.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRegistrationRequest {
	
	private int mobileNumber;
	private String emailId;
}
