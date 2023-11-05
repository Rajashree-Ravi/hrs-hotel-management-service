package com.hrs.hotelmanagementservice.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrs.hotelmanagementservice.entities.RoomInventory;
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
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved reservation"),
			@ApiResponse(code = 404, message = "Reservation with specified reservation id not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	private ResponseEntity<Boolean> checkAvailability(@RequestParam @Valid RoomType roomType) {
		List<RoomInventory> inventory = hotelManagementService.getInventoryByRoomTypeAndAvailability(roomType, true);

		if (inventory.size() > 0)
			return new ResponseEntity<>(true, HttpStatus.OK);
		else
			return new ResponseEntity<>(false, HttpStatus.OK);
	}

}
