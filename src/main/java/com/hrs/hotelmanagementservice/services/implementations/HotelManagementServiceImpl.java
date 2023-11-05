package com.hrs.hotelmanagementservice.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrs.hotelmanagementservice.entities.Room;
import com.hrs.hotelmanagementservice.entities.RoomInventory;
import com.hrs.hotelmanagementservice.exceptions.InventoryNotFoundException;
import com.hrs.hotelmanagementservice.exceptions.RoomNotFoundException;
import com.hrs.hotelmanagementservice.models.RoomDto;
import com.hrs.hotelmanagementservice.models.RoomType;
import com.hrs.hotelmanagementservice.repositories.RoomInventoryRepository;
import com.hrs.hotelmanagementservice.repositories.RoomRepository;
import com.hrs.hotelmanagementservice.services.HotelManagementService;

@Service
public class HotelManagementServiceImpl implements HotelManagementService {

	private static final Logger log = LoggerFactory.getLogger(HotelManagementServiceImpl.class);

	@Autowired
	private RoomInventoryRepository inventoryRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private ModelMapper mapper;

	/**
	 * Retrieves a room with unique room id
	 */
	@Override
	public RoomDto getRoomById(@Valid long id) {
		Optional<Room> room = roomRepository.findById(id);
		if (room.isPresent())
			return mapper.map(room.get(), RoomDto.class);
		else
			throw new RoomNotFoundException("Room with id: " + id + " not found");
	}

	/**
	 * Retrieves all the existing rooms with unique customer id
	 */
	@Override
	public List<RoomDto> getRoomsByCustomerId(@Valid long customerId) {
		List<RoomDto> rooms = new ArrayList<>();
		roomRepository.findByCustomerId(customerId).forEach(room -> {
			rooms.add(mapper.map(room, RoomDto.class));
		});
		return rooms;
	}

	/**
	 * Create a new room
	 */
	@Override
	public RoomDto createRoom(@Valid RoomDto roomDto) {
		Room room = mapper.map(roomDto, Room.class);
		return mapper.map(roomRepository.save(room), RoomDto.class);
	}

	/**
	 * Updates a room with unique room id
	 */
	@Override
	public RoomDto updateRoom(@Valid long id, @Valid RoomDto roomDto) {
		Optional<Room> roomFound = roomRepository.findById(id);

		if (roomFound.isPresent()) {
			Optional<Room> updatedRoom = roomFound.map(existingRoom -> {
				Room room = mapper.map(roomDto, Room.class);
				return roomRepository.save(existingRoom.updateWith(room));
			});

			return mapper.map(updatedRoom.get(), RoomDto.class);
		} else
			throw new RoomNotFoundException("Room with id: " + id + " not found");
	}

	/**
	 * Deletes a room with unique room id
	 */
	@Override
	public void deleteRoom(@Valid long id) {
		if (getRoomById(id) != null) {
			roomRepository.deleteById(id);
			log.info("Room deleted Successfully");
		} else {
			throw new RoomNotFoundException("Room with id: " + id + " not found");
		}
	}

	/**
	 * Retrieves a room inventory with room type and availability
	 */
	@Override
	public List<RoomInventory> getInventoryByRoomTypeAndAvailability(@Valid RoomType type,
			@Valid boolean availability) {
		return inventoryRepository.findByRoomTypeAndAvailability(type, availability);
	}

	/**
	 * Updates a room inventory
	 */
	@Override
	public void updateInventory(@Valid long id, @Valid RoomDto room, String type) {
		Optional<RoomInventory> inventoryFound = inventoryRepository.findById(id);

		if (inventoryFound.isPresent()) {
			RoomInventory inventory = inventoryFound.get();
			int roomCount = inventory.getAvailableRooms();

			if (type.equals("reservation")) {
				roomCount = roomCount - room.getNumberOfRooms();
			} else if (type.equals("cancellation")) {
				roomCount = roomCount + room.getNumberOfRooms();
			}

			inventory.setAvailableRooms(roomCount);

			if (inventory.getAvailableRooms() > 0)
				inventory.setAvailability(true);
			else
				inventory.setAvailability(false);

			inventoryRepository.save(inventory);

			log.info("Inventory updated by " + room.getNumberOfRooms() + " rooms.");
		} else
			throw new InventoryNotFoundException("Inventory with id: " + id + " not found");
	}

}
