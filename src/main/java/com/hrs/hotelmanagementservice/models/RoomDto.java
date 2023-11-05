package com.hrs.hotelmanagementservice.models;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Class representing a Room in hotel reservation system.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

	@ApiModelProperty(notes = "Unique identifier of the Room.", example = "1")
	private Long id;

	@ApiModelProperty(notes = "Unique identifier of the Room Inventory.", example = "1", required = true)
	@NotNull(message = "Invalid inventoryId: InventoryId may not be null.")
	private Long inventoryId;

	@ApiModelProperty(notes = "Unique identifier of the Customer.", example = "1", required = true)
	@NotNull(message = "Invalid customerId: CustomerId may not be null.")
	private Long customerId;

	@ApiModelProperty(notes = "Number of rooms.", example = "1", required = true)
	@NotNull(message = "Invalid numberOfRooms: Number of rooms may not be null.")
	private Integer numberOfRooms;

	@ApiModelProperty(notes = "Number of adults.", example = "2", required = true)
	@NotNull(message = "Invalid numberOfAdults: Number of adults may not be null.")
	private Integer numberOfAdults;

	@ApiModelProperty(notes = "Number of children.", example = "2", required = true)
	@NotNull(message = "Invalid numberOfChildren: Number of children may not be null.")
	private Integer numberOfChildren;

	@ApiModelProperty(notes = "Start Date of room occupancy.", example = "23/11/2023", required = true)
	@NotNull(message = "Invalid startDate: Start Date may not be null.")
	private LocalDate occupancyStartDate;

	@ApiModelProperty(notes = "End Date of room occupancy.", example = "26/11/2023", required = true)
	@NotNull(message = "Invalid endDate: End Date may not be null.")
	private LocalDate occupancyEndDate;

}
