package com.lblw.application.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lblw.application.model.dto.ErrorResponse;

/**
 * 
 * @author rajesh.l.singh, Handling all exceptions
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		return new ResponseEntity<Object>(error("Server Error", ex, null, request), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {

		return new ResponseEntity<Object>(error("User not found", ex, null, request), HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<Object>(error("Validation failed", null, ex, request), HttpStatus.BAD_REQUEST);
	}

	private ErrorResponse error(String msg, Exception e, MethodArgumentNotValidException ve, WebRequest req) {
		List<String> details = new ArrayList<String>();
		if (null != ve) {
			ve.getBindingResult().getAllErrors().stream().forEach(x -> details.add(x.getDefaultMessage()));
		} else {
			details.add(e.getLocalizedMessage());
		}
		details.add(req.getDescription(false));
		return ErrorResponse.builder().message(msg).details(details).build();

	}
}
