package com.bridgelabz.fundoonote.user.service;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonote.user.dto.LoginDto;
import com.bridgelabz.fundoonote.user.dto.RegisterDto;
import com.bridgelabz.fundoonote.user.dto.UserDto;
import com.bridgelabz.fundoonote.user.exception.UserException;
import com.bridgelabz.fundoonote.user.models.User;
import com.bridgelabz.fundoonote.user.utility.UserResponse;

@Service
public interface IUserService {

	UserResponse registration(RegisterDto registerDto) throws UserException;

	UserResponse login(LoginDto loginDto) throws UserException;

	UserResponse userVerification(String token);

	UserResponse updateUser(int id, RegisterDto registerDto);

	UserResponse deleteUser(int id) throws UserException;

	UserResponse getById(int id) throws UserException;

	User forgotPassword(String email, String password) throws UserException;

	List<UserDto> findAll();
}
