package com.booking.hotelservice.controller;

import com.booking.hotelservice.dto.HotelDTO;
import com.booking.hotelservice.dto.response.ResponseSuccess;
import com.booking.hotelservice.dto.RoomDTO;
import com.booking.hotelservice.exception.ResourceNotFoundException;
import com.booking.hotelservice.model.Room;
import com.booking.hotelservice.service.RoomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

  private final RoomService roomService;

  @GetMapping
  public ResponseSuccess getAllRooms() {
    List<Room> rooms = roomService.getAllRooms();
    return new ResponseSuccess(HttpStatus.OK, "Fetched room list successfully", rooms);
  }

  @GetMapping("/{id}")
  public ResponseSuccess getRoomById(@PathVariable("id") @Min(0) Long id) {
    return roomService.getRoomById(id)
        .map(room -> new ResponseSuccess(HttpStatus.OK, "Room found", room))
        .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
  }


  @PostMapping
  public ResponseSuccess createRoom(
      @RequestPart(value = "room") @Valid RoomDTO room,
      @RequestPart(value = "images", required = false) MultipartFile[] images
  ) {
    Room created = roomService.createRoom(room, images);
    return new ResponseSuccess(HttpStatus.CREATED, "Room created successfully", created);
  }

  @PutMapping("/{id}")
  public ResponseSuccess updateRoom(@PathVariable("id") @Min(0) Long id,@Valid @RequestBody RoomDTO room) {
    Room updated = roomService.updateRoom(id, room);
    return new ResponseSuccess(HttpStatus.OK, "Room updated successfully", updated);
  }

  @DeleteMapping("/{id}")
  public ResponseSuccess deleteRoom(@PathVariable("id") @Min(0) Long id) {
    roomService.deleteRoom(id);
    return new ResponseSuccess(HttpStatus.OK, "Room deleted successfully");
  }
}
