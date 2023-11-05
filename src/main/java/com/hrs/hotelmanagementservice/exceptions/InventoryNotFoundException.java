package com.hrs.hotelmanagementservice.exceptions;

public class InventoryNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InventoryNotFoundException() {
		super();
	}

	public InventoryNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
