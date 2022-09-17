package com.bridgelabz;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;

import com.bridgelabz.fundoonote.user.utility.EmailSender;
import com.bridgelabz.fundoonote.user.utility.TokenUtil;

@SpringBootApplication
public class FundoonoteApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	@Primary
	@Autowired
	public TokenUtil tokenManager() {
		return new TokenUtil();
	}

	@Autowired
	private EmailSender emailSender;

	public static void main(String[] args) {
		SpringApplication.run(FundoonoteApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() {
		emailSender.sendSimpleEmail("srajashekhar01@gmail.com", "Hi, Am Spring, Sending the mail to your notice ",
				"Mail from Spring Boot");
	}

}
