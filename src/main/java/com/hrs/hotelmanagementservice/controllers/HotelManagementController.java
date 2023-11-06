package com.hrs.hotelmanagementservice.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrs.hotelmanagementservice.entities.RoomInventory;
import com.hrs.hotelmanagementservice.models.RoomDto;
import com.hrs.hotelmanagementservice.models.RoomType;
import com.hrs.hotelmanagementservice.services.HotelManagementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(produces = "application/json", value = "Operations pertaining to manage rooms in hotel reservation system")
@RestController
@RequestMapping("/api")
public class HotelManagementController {

	private static final Logger log = LoggerFactory.getLogger(HotelManagementController.class);

	@Autowired
	private HotelManagementService hotelManagementService;

	@GetMapping("/welcome")
	private ResponseEntity<String> displayWelcomeMessage() {
		return new ResponseEntity<>("Welcome to hotel management service !!", HttpStatus.OK);
	}

	@GetMapping("/checkAvailability")
	@ApiOperation(value = "Check room availability", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully checked for availability"),
			@ApiResponse(code = 404, message = "Inventory not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	private ResponseEntity<Boolean> checkAvailability(@RequestParam @Valid RoomType roomType) {
		List<RoomInventory> inventory = hotelManagementService.getInventoryByRoomTypeAndAvailability(roomType, true);
		log.info("Inventory size: " + inventory.size());
		if (inventory.size() > 0)
			return new ResponseEntity<>(true, HttpStatus.OK);
		else
			return new ResponseEntity<>(false, HttpStatus.OK);
	}

	@GetMapping("/retrieve/{id}")
	@ApiOperation(value = "Retrieve the room with the specified room id", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved room"),
			@ApiResponse(code = 404, message = "Room with specified room id not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	private ResponseEntity<RoomDto> getRoomById(@PathVariable("id") @Valid long id) {
		RoomDto room = hotelManagementService.getRoomById(id);
		return new ResponseEntity<>(room, HttpStatus.OK);
	}

	@PostMapping("/create")
	@ApiOperation(value = "Create a new room", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created the room"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	private ResponseEntity<RoomDto> createRoom(@RequestBody @Valid RoomDto room, @RequestParam @Valid RoomType type) {
		RoomDto savedRoom = hotelManagementService.createRoom(room, type);
		return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	@ApiOperation(value = "Update a room information", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated room information"),
			@ApiResponse(code = 404, message = "Room with specified room id not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public ResponseEntity<RoomDto> updateRoom(@PathVariable("id") @Valid long id, @RequestBody @Valid RoomDto room) {
		return new ResponseEntity<>(hotelManagementService.updateRoom(id, room), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Delete a room", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully deleted room information"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	private ResponseEntity<String> deleteRoom(@PathVariable("id") @Valid long id) {
		hotelManagementService.deleteRoom(id);
		return new ResponseEntity<>("Room deleted successfully", HttpStatus.OK);
	}

}
