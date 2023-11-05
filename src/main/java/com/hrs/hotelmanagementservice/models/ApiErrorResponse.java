package com.hrs.hotelmanagementservice.models;

import java.util.List;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApiErrorResponse {
	private final Integer statusCode;
	private final String statusName;
	private final String path;
	private final String method;
	private final LocalDateTime timestamp;
	private final String errorCode;
	private final List<String> errors;
}