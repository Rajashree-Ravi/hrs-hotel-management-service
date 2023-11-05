package com.hrs.hotelmanagementservice.services;

import java.util.List;

import javax.validation.Valid;

import com.hrs.hotelmanagementservice.entities.RoomInventory;
import com.hrs.hotelmanagementservice.models.RoomDto;
import com.hrs.hotelmanagementservice.models.RoomType;

public interface HotelManagementService {
	
	List<RoomInventory> getInventoryByRoomTypeAndAvailability(@Valid RoomType type, @Valid boolean availability);
	
	void updateInventory(@Valid long id, @Valid RoomDto room, String type);

	RoomDto getRoomById(@Valid long id);

	List<RoomDto> getRoomsByCustomerId(@Valid long customerId);

	RoomDto createRoom(@Valid RoomDto room);

	RoomDto updateRoom(@Valid long id, @Valid RoomDto room);

	void deleteRoom(@Valid long id);

}
