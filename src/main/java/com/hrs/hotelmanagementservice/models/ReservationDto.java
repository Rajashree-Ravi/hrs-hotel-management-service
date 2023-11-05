package com.hrs.hotelmanagementservice.models;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Class representing a Reservation in hotel reservation system.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

	@ApiModelProperty(notes = "Unique identifier of the Reservation.", example = "1")
	private Long id;

	@ApiModelProperty(notes = "Unique identifier of the Customer.", example = "1", required = true)
	@NotNull(message = "Invalid customerId: CustomerId may not be null.")
	private Long customerId;

	@ApiModelProperty(notes = "Unique identifier of the Hotel Room.")
	private Long hotelId;

	@ApiModelProperty(notes = "Unique identifier of the Payment.")
	private Long paymentId;

	@ApiModelProperty(notes = "Start Date of reservation.", example = "23/11/2023", required = true)
	@NotNull(message = "Invalid startDate: Start Date may not be null.")
	private LocalDate startDate;

	@ApiModelProperty(notes = "End Date of reservation.", example = "26/11/2023", required = true)
	@NotNull(message = "Invalid endDate: End Date may not be null.")
	private LocalDate endDate;

	@ApiModelProperty(notes = "Status of the reservation.", example = "BOOKED", required = true)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Invalid status: Reservation status may not be null.")
	private ReservationStatus status;

	@ApiModelProperty(notes = "Details for the reservation.", required = true)
	@NotNull(message = "Invalid reservationInfo: Reservation details may not be null.")
	private ReservationInfo reservationInfo;

}
