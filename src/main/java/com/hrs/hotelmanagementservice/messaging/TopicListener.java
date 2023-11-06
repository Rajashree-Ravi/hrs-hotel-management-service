package com.hrs.hotelmanagementservice.messaging;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.hrs.hotelmanagementservice.models.ReservationDto;
import com.hrs.hotelmanagementservice.models.RoomDto;
import com.hrs.hotelmanagementservice.services.HotelManagementService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TopicListener {

	@Value("${consumer.config.reservation.topic.name}")
	private String reservationTopicName;

	@Value("${consumer.config.cancellation.topic.name}")
	private String cancellationTopicName;

	@Autowired
	private HotelManagementService hotelManagementService;

	@KafkaListener(id = "${consumer.config.reservation.topic.name}", topics = "${consumer.config.reservation.topic.name}", groupId = "${consumer.config.group-id}")
	public void consumeRoomReservation(ConsumerRecord<String, ReservationDto> payload) {
		log.info("Topic : {}", reservationTopicName);
		log.info("Key : {}", payload.key());
		log.info("Headers : {}", payload.headers());
		log.info("Partion : {}", payload.partition());
		log.info("Reservation : {}", payload.value());

		ReservationDto reservation = payload.value();
		RoomDto roomDto = hotelManagementService.getRoomById(reservation.getHotelId());
		hotelManagementService.updateInventory(roomDto.getInventoryId(), roomDto, "reservation");
	}

	@KafkaListener(id = "${consumer.config.cancellation.topic.name}", topics = "${consumer.config.cancellation.topic.name}", groupId = "${consumer.config.group-id}")
	public void consumeRoomCancellation(ConsumerRecord<String, ReservationDto> payload) {
		log.info("Topic : {}", cancellationTopicName);
		log.info("Key : {}", payload.key());
		log.info("Headers : {}", payload.headers());
		log.info("Partion : {}", payload.partition());
		log.info("Reservation : {}", payload.value());

		ReservationDto reservation = payload.value();

		RoomDto roomDto = hotelManagementService.getRoomById(reservation.getHotelId());
		hotelManagementService.updateInventory(roomDto.getInventoryId(), roomDto, "cancellation");
		hotelManagementService.deleteRoom(roomDto.getId());
	}

}