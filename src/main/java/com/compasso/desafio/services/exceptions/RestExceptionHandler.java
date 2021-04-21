package com.compasso.desafio.services.exceptions;

import com.compasso.desafio.services.exceptions.error.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

//	@ExceptionHandler(value = {Exception.class})
//	public ResponseEntity<Object> handleAnyExceptions(Exception e, WebRequest request) {
//		String errorDescription = e.getLocalizedMessage();
//		if (errorDescription == null) {
//			errorDescription = e.toString();
//		}
//		ErrorMessage errorMessage = new ErrorMessage(new Date(), new ArrayList<>());
//		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	@ExceptionHandler(value = {RuntimeException.class})
	public ResponseEntity<Object> handleDuplicateKeysExceptions(Exception e, WebRequest request) {
		String errorDescription = e.getMessage();
		ErrorMessage errorMessage = new ErrorMessage(new Date(), Arrays.asList(errorDescription));
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var errorDescription = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorDescription);
        return new ResponseEntity<>(errorMessage, status);
    }
}