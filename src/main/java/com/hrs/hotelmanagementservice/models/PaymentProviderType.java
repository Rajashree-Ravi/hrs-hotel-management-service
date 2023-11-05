package com.hrs.hotelmanagementservice.models;

import java.util.Random;

public enum PaymentProviderType {
	CARD, UPI;

	private static final Random random = new Random();

	public static PaymentProviderType randomPaymentProvider() {
		PaymentProviderType[] providers = values();
		return providers[random.nextInt(providers.length)];
	}

}
