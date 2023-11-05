package com.hrs.hotelmanagementservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrs.hotelmanagementservice.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	List<Room> findByCustomerId(Long customerId);

}
