package com.hrs.hotelmanagementservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrs.hotelmanagementservice.entities.RoomInventory;
import com.hrs.hotelmanagementservice.models.RoomType;

public interface RoomInventoryRepository extends JpaRepository<RoomInventory, Long> {

	List<RoomInventory> findByRoomTypeAndAvailability(RoomType type, boolean availablity);

}
