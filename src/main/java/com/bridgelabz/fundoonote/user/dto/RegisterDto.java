package com.bridgelabz.fundoonote.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

	private String name;
	private String email;
	private String phoneNumber;
	private String password;

}
