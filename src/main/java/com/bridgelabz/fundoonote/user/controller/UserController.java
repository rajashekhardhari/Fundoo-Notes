package com.bridgelabz.fundoonote.user.controller;

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

import com.bridgelabz.fundoonote.user.dto.LoginDto;
import com.bridgelabz.fundoonote.user.dto.RegisterDto;
import com.bridgelabz.fundoonote.user.dto.UserDto;
import com.bridgelabz.fundoonote.user.exception.UserException;
import com.bridgelabz.fundoonote.user.service.IUserService;
import com.bridgelabz.fundoonote.user.utility.UserResponse;

@RestController
@RequestMapping("/home")		
@CrossOrigin("*")
public class UserController {
	

	@Autowired
	private IUserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> login(@RequestBody LoginDto loginDto) throws UserException {
		ResponseEntity<UserResponse> response = new ResponseEntity<UserResponse>(userService.login(loginDto),
				HttpStatus.OK);
		return response;
	}

	@PostMapping("/register")
	public ResponseEntity<UserResponse> register(@RequestBody RegisterDto registerDto) throws UserException {
		ResponseEntity<UserResponse> response = new ResponseEntity<UserResponse>(userService.registration(registerDto),
				HttpStatus.OK);
		return response;
	}

	@GetMapping("/verify")
	public ResponseEntity<UserResponse> verification(@RequestParam("token") String token) {
		UserResponse result = userService.userVerification(token);

		return new ResponseEntity<UserResponse>(result, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UserResponse> deleteUser(@RequestParam int id) throws UserException {
		ResponseEntity<UserResponse> response = new ResponseEntity<UserResponse>(userService.deleteUser(id),
				HttpStatus.OK);
		return response;

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable Integer id, @RequestBody RegisterDto registerDto) {
		ResponseEntity<UserResponse> response = new ResponseEntity<UserResponse>(
				userService.updateUser(id, registerDto), HttpStatus.OK);
		return response;
	}

	@GetMapping("/getdatabyid")
	public ResponseEntity<UserResponse> getUserById1(@RequestParam int id) throws UserException {
		ResponseEntity<UserResponse> response = new ResponseEntity<UserResponse>(userService.getById(id),
				HttpStatus.OK);
		return response;
	}
	

	@GetMapping("/get")
	public List<UserDto> getList() {
		return userService.findAll();
	}

}