package com.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.backend.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFound(
            TaskNotFoundException ex) {

        ErrorResponse error =
                new ErrorResponse(ex.getMessage());

        return new ResponseEntity<>(
                error,
                HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(TagNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleTagNotFound(
	        TagNotFoundException ex) {

	    ErrorResponse error =
	            new ErrorResponse(ex.getMessage());

	    return new ResponseEntity<>(
	            error,
	            HttpStatus.NOT_FOUND);
	}

}
