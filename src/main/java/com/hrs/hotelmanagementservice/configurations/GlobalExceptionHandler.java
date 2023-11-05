package com.hrs.hotelmanagementservice.configurations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hrs.hotelmanagementservice.exceptions.InventoryNotFoundException;
import com.hrs.hotelmanagementservice.exceptions.RoomNotFoundException;
import com.hrs.hotelmanagementservice.models.ApiErrorResponse;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleValidationErrors(final MethodArgumentNotValidException ex,
			final HttpServletRequest request) {

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		ApiErrorResponse response = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
				request.getRequestURI(), request.getMethod(), LocalDateTime.now(), "validation-error", errors);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RoomNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleRoomNotFoundExceptions(final RoomNotFoundException ex,
			final HttpServletRequest request) {

		List<String> errors = Collections.singletonList(ex.getMessage());

		ApiErrorResponse response = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),
				request.getRequestURI(), request.getMethod(), LocalDateTime.now(), "room-not-found", errors);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InventoryNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleRoomNotFoundExceptions(final InventoryNotFoundException ex,
			final HttpServletRequest request) {

		List<String> errors = Collections.singletonList(ex.getMessage());

		ApiErrorResponse response = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),
				request.getRequestURI(), request.getMethod(), LocalDateTime.now(), "inventory-not-found", errors);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ApiErrorResponse> handleGeneralExceptions(final Exception ex,
			final HttpServletRequest request) {

		List<String> errors = Collections.singletonList(ex.getMessage());

		ApiErrorResponse response = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.name(), request.getRequestURI(), request.getMethod(),
				LocalDateTime.now(), "internal-server-error", errors);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<ApiErrorResponse> handleRuntimeExceptions(final RuntimeException ex,
			final HttpServletRequest request) {

		List<String> errors = Collections.singletonList(ex.getMessage());

		ApiErrorResponse response = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.name(), request.getRequestURI(), request.getMethod(),
				LocalDateTime.now(), "internal-server-error", errors);

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
