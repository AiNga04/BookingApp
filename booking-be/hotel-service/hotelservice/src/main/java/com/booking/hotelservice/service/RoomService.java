package com.booking.hotelservice.service;

import com.booking.hotelservice.dto.RoomDTO;
import com.booking.hotelservice.exception.ResourceNotFoundException;
import com.booking.hotelservice.mapper.RoomMapper;
import com.booking.hotelservice.model.Hotel;
import com.booking.hotelservice.model.Room;
import com.booking.hotelservice.repository.HotelRepository;
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

  private final HotelRepository hotelRepository;
  private final RoomRepository roomRepository;
  private final RoomMapper roomMapper;

  public List<Room> getAllRooms() {
    return roomRepository.findAll();
  }

  public Optional<Room> getRoomById(Long id) {
    return roomRepository.findById(id);
  }


  public Room createRoom(RoomDTO room) {

    return roomRepository.save(roomMapper.toRoom(room));
  }

  public Room updateRoom(Long id, RoomDTO updatedRoom) {
    Hotel hotel = hotelRepository.findById(updatedRoom.getHotelId()).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + updatedRoom.getHotelId()));
    return roomRepository.findById(id)
        .map(room -> {
          room.setTypeRoom(updatedRoom.getTypeRoom());
          room.setCapacity(updatedRoom.getCapacity());
          room.setPricePerNight(updatedRoom.getPricePerNight());
          room.setAvailable(updatedRoom.isAvailable());
          room.setHotel(hotel);
          return roomRepository.save(room);
        })
        .orElseThrow(() -> new RuntimeException("Room not found"));
  }

  public void deleteRoom(Long id) {
    roomRepository.deleteById(id);
  }
}
