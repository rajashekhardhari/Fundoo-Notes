package com.bridgelabz.fundoonote.label.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class LabelGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(LabelException.class)
	public ResponseEntity<Object> userNotFoundResponse(LabelException e, WebRequest request) {
		LabelErrorResponse errorResponse = new LabelErrorResponse(LocalDateTime.now(), e.getMessage());
		return handleExceptionInternal(e, errorResponse, null, HttpStatus.NOT_FOUND, request);
	}

}