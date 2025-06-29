package com.booking.hotelservice.controller;

import com.booking.hotelservice.dto.ResponseSuccess;
import com.booking.hotelservice.model.Room;
import com.booking.hotelservice.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

  private final RoomService roomService;

  @GetMapping
  public ResponseSuccess getAllRooms() {
    List<Room> rooms = roomService.getAllRooms();
    return new ResponseSuccess(HttpStatus.OK, "Fetched room list successfully", rooms);
  }

  @GetMapping("/{id}")
  public ResponseSuccess getRoomById(@PathVariable Long id) {
    return roomService.getRoomById(id)
        .map(room -> new ResponseSuccess(HttpStatus.OK, "Room found", room))
        .orElseThrow(() -> new RuntimeException("Room not found"));
  }

  @PostMapping
  public ResponseSuccess createRoom(@RequestBody Room room) {
    Room created = roomService.createRoom(room);
    return new ResponseSuccess(HttpStatus.CREATED, "Room created successfully", created);
  }

  @PutMapping("/{id}")
  public ResponseSuccess updateRoom(@PathVariable Long id, @RequestBody Room room) {
    Room updated = roomService.updateRoom(id, room);
    return new ResponseSuccess(HttpStatus.OK, "Room updated successfully", updated);
  }

  @DeleteMapping("/{id}")
  public ResponseSuccess deleteRoom(@PathVariable Long id) {
    roomService.deleteRoom(id);
    return new ResponseSuccess(HttpStatus.OK, "Room deleted successfully");
  }
}
