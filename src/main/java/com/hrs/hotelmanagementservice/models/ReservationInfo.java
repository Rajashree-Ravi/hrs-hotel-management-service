package com.hrs.hotelmanagementservice.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInfo {

	@ApiModelProperty(notes = "Type of room.", example = "DELUXE_ROOM", required = true)
	@NotNull(message = "Invalid roomType: Room type may not be null.")
	private RoomType roomType;
	
	@ApiModelProperty(notes = "Number of rooms.", example = "1", required = true)
	@NotNull(message = "Invalid numberOfRooms: Number of rooms may not be null.")
	private Integer numberOfRooms;

	@ApiModelProperty(notes = "Number of adults.", example = "2", required = true)
	@NotNull(message = "Invalid numberOfAdults: Number of adults may not be null.")
	private Integer numberOfAdults;

	@ApiModelProperty(notes = "Number of children.", example = "2", required = true)
	@NotNull(message = "Invalid numberOfChildren: Number of children may not be null.")
	private Integer numberOfChildren;

	@ApiModelProperty(notes = "Payment gateway provider.", example = "CARD", required = true)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Invalid provider: Payment provider may not be null.")
	private PaymentProviderType provider;

	@ApiModelProperty(notes = "Type of the payment.", example = "REFUND", required = true)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Invalid paymentType: Payment type may not be null.")
	private PaymentType paymentType;

	@ApiModelProperty(notes = "Amount to be paid.", example = "1000.00", required = true)
	@NotNull(message = "Invalid amount: Payment amount may not be null.")
	private BigDecimal paymentAmount;

	@ApiModelProperty(notes = "Card number for card payment.", example = "432156789876")
	private String cardNumber;

	@ApiModelProperty(notes = "Card holder name for card payment.", example = "RAJESH")
	private String cardHolderName;

	@ApiModelProperty(notes = "Cvv for card payment.", example = "456")
	private String cvv;

	@ApiModelProperty(notes = "Virtual payment address for UPI payment.", example = "9876543210@paytm")
	private String virtualPaymentAddress;

	@ApiModelProperty(notes = "User comments on UPI payment.", example = "Mobile Recharge")
	private String userComments;

}
