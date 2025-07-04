package com.booking.hotelservice.service;

import com.booking.hotelservice.dto.RoomDTO;
import com.booking.hotelservice.exception.ResourceNotFoundException;
import com.booking.hotelservice.mapper.RoomMapper;
import com.booking.hotelservice.model.Hotel;
import com.booking.hotelservice.model.Room;
import com.booking.hotelservice.repository.HotelRepository;
import com.booking.hotelservice.repository.RoomRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

  private final HotelRepository hotelRepository;
  private final RoomRepository roomRepository;
  private final RoomMapper roomMapper;
  private final CloudinaryService cloudinaryService;

  public List<Room> getAllRooms() {
    return roomRepository.findAll();
  }

  public Optional<Room> getRoomById(Long id) {
    return roomRepository.findById(id);
  }


  public Room createRoom(RoomDTO room, MultipartFile[] imagesRoom) {

    List<String> listImageUrl = new ArrayList<>();
    if(imagesRoom != null && imagesRoom.length > 0) {

      for (MultipartFile image : imagesRoom) {
        try {
          Map<String, Object> data = this.cloudinaryService.upload(image);
          String imageUrl = (String) data.get("secure_url");
          listImageUrl.add(imageUrl);
        } catch (Exception e) {
          throw new RuntimeException("Failed to upload image: " + image.getOriginalFilename());
        }
      }

      room.setListImageUrl(listImageUrl);
    }

    return roomRepository.save(roomMapper.toRoom(room));
  }

  public Room updateRoom(Long id, RoomDTO updatedRoom) {
    Hotel hotel = hotelRepository.findById(updatedRoom.getHotelId()).orElseThrow(
        () -> new ResourceNotFoundException(
            "Hotel not found with id: " + updatedRoom.getHotelId()));
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
