package com.bridgelabz.fundoonote.user.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserErrorResponse {

	private LocalDateTime timeStamp;
	private String message;

}
