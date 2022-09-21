package com.bridgelabz.fundoonote.note.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoteErrorResponse {

	private LocalDateTime timeStamp;
	private String message;

}
