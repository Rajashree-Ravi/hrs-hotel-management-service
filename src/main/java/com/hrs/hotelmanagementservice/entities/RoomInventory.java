package com.hrs.hotelmanagementservice.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.hrs.hotelmanagementservice.models.RoomType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "inventory")
public class RoomInventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Invalid total: Total number of room may not be null.")
	private Integer totalNumberOfRooms;

	@NotNull(message = "Invalid availableRooms: Number of available rooms may not be null.")
	private Integer availableRooms;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Invalid roomType: Room type may not be null.")
	private RoomType roomType;

	@NotNull(message = "Invalid roomOccupancy: Room occupancy may not be null.")
	private Integer roomOccupancy;

	@NotNull(message = "Invalid availability: Availability status may not be null.")
	private Boolean availability;

	public RoomInventory updateWith(RoomInventory inventory) {
		return new RoomInventory(this.id, inventory.totalNumberOfRooms, inventory.availableRooms, inventory.roomType,
				inventory.roomOccupancy, inventory.availability);
	}

}
