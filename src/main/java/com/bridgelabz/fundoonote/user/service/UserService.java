package com.bridgelabz.fundoonote.user.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.TypeToken;

import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonote.user.dto.LoginDto;
import com.bridgelabz.fundoonote.user.dto.RegisterDto;
import com.bridgelabz.fundoonote.user.dto.UserDto;

import com.bridgelabz.fundoonote.user.exception.UserException;
import com.bridgelabz.fundoonote.user.models.User;
import com.bridgelabz.fundoonote.user.repository.UserRepo;
import com.bridgelabz.fundoonote.user.utility.EmailSender;
import com.bridgelabz.fundoonote.user.utility.TokenUtil;
import com.bridgelabz.fundoonote.user.utility.UserResponse;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TokenUtil tokenManager;

	@Autowired
	EmailSender emailSender;

	@Override
	public UserResponse registration(RegisterDto registerDto) throws UserException {
		Optional<User> find = userRepo.findByEmail(registerDto.getEmail());
		if (find.isPresent()) {
			throw new UserException(String.format("!!! User Details has been already entered in the Database !!!"));
		}
		User user = modelMapper.map(registerDto, User.class);
		User store = userRepo.save(user);
		String url = "http://localhost:8080/home/verify?token=" + tokenManager.encodeToken(store.getId());
		emailSender.sendSimpleEmail(store.getEmail(),
				"Hi, Am Spring, Sending the mail to your notice , confirm your account ,Please click here:" + url,
				"Mail from Spring Boot");
		UserResponse response = new UserResponse(
				"The response message : User Information Sucessfully added to the DataBase", 200, store);
		return response;
	}

	@Override
	public UserResponse login(LoginDto loginDto) throws UserException {
		String token;
		Optional<User> isUser = userRepo.findByEmail(loginDto.getEmail());

		if (isUser.isPresent()) {
			String password = isUser.get().getPassword();
			if (password.equals(loginDto.getPassword())) {
				token = tokenManager.encodeToken(isUser.get().getId());
				return new UserResponse("User is Found", 300, token);
			} else
				throw new UserException("Password is Wrong");
		}
		return null;
	}

	@Override
	public UserResponse userVerification(String token) {
		int id = tokenManager.decodeToken(token);
		User isUser = userRepo.findById(id).orElseThrow(() -> new UserException("invalid usedID"));

		isUser.setIsVerified(true);
		userRepo.save(isUser);
		return new UserResponse("user verified successfully", 200, id);

	}

	@Override
	public User forgotPassword(String email, String password) throws UserException {
		Optional<User> isUser = userRepo.findByEmail(email);
		if (isUser.isPresent()) {
			isUser.get().setPassword(password);
			return userRepo.save(isUser.get());
		} else {
			throw new UserException("Invalid Email");
		}
	}

	@Override
	public UserResponse updateUser(int id, RegisterDto registerDto) {
		User change = modelMapper.map(registerDto, User.class);
		change.setId(id);
		User update = userRepo.save(change);
		UserResponse response = new UserResponse(
				"The response message : User Information Sucessfully updated to the DataBase", 200, update);
		return response;
	}

	@Override
	public UserResponse deleteUser(int id) throws UserException {
		Optional<User> find = userRepo.findById(id);
		if (!find.isPresent()) {
			throw new UserException(String.format("!!! User Details not found with id " + id
					+ ". Kindly check the entered Information for Deleting !!!"));
		}
		userRepo.deleteById(id);
		UserResponse response = new UserResponse(
				"The response message : User Information Sucessfully deleted from the DataBase", 200, id);
		return response;
	}

	@Override
	public UserResponse getById(int id) throws UserException {
		Optional<User> find = userRepo.findById(id);
		if (!find.isPresent()) {
			throw new UserException(
					String.format("!!! User not found with id " + id + ". Kindly check the entered Information !!!"));
		}
		UserDto dto = modelMapper.map(find, UserDto.class);
		UserResponse response = new UserResponse(
				"The response message : User Information Sucessfully retrieved from the DataBase", 200, dto);

		return response;
	}

	@Override
	public List<UserDto> findAll() {
		List<User> user = userRepo.findAll();
	    Type userType = new TypeToken<List<UserDto>>() {

		}.getType();
		List<UserDto> userDto = modelMapper.map(user, userType);
		return userDto;
	}

}
