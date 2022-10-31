package com.bridgelabz.fundoonote.label.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LabelErrorResponse {

	private LocalDateTime timeStamp;
	private String message;

}
