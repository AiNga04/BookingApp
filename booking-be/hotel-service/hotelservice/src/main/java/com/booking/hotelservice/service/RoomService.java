package com.booking.hotelservice.service;

import com.booking.hotelservice.model.Room;
import com.booking.hotelservice.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

  private final RoomRepository roomRepository;

  public List<Room> getAllRooms() {
    return roomRepository.findAll();
  }

  public Optional<Room> getRoomById(Long id) {
    return roomRepository.findById(id);
  }

  public Room createRoom(Room room) {
    return roomRepository.save(room);
  }

  public Room updateRoom(Long id, Room updatedRoom) {
    return roomRepository.findById(id)
        .map(room -> {
          room.setTypeRoom(updatedRoom.getTypeRoom());
          room.setCapacity(updatedRoom.getCapacity());
          room.setPricePerNight(updatedRoom.getPricePerNight());
          room.setAvailable(updatedRoom.isAvailable());
          room.setHotel(updatedRoom.getHotel());
          return roomRepository.save(room);
        })
        .orElseThrow(() -> new RuntimeException("Room not found"));
  }

  public void deleteRoom(Long id) {
    roomRepository.deleteById(id);
  }
}
