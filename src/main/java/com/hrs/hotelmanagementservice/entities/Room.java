package com.hrs.hotelmanagementservice.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "room")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Invalid inventoryId: InventoryId may not be null.")
	private Long inventoryId;

	@NotNull(message = "Invalid customerId: CustomerId may not be null.")
	private Long customerId;

	@NotNull(message = "Invalid numberOfRooms: Number of rooms may not be null.")
	private Integer numberOfRooms;

	@NotNull(message = "Invalid numberOfAdults: Number of adults may not be null.")
	private Integer numberOfAdults;

	@NotNull(message = "Invalid numberOfChildren: Number of children may not be null.")
	private Integer numberOfChildren;

	@NotNull(message = "Invalid startDate: Start Date may not be null.")
	private LocalDate occupancyStartDate;

	@NotNull(message = "Invalid endDate: End Date may not be null.")
	private LocalDate occupancyEndDate;

	public Room updateWith(Room room) {
		return new Room(this.id, room.inventoryId, room.customerId, room.numberOfRooms, room.numberOfAdults,
				room.numberOfChildren, room.occupancyStartDate, room.occupancyEndDate);
	}

}
