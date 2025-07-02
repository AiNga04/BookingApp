package com.booking.hotelservice.service;

import com.booking.hotelservice.dto.HotelDTO;
import com.booking.hotelservice.exception.ResourceNotFoundException;
import com.booking.hotelservice.mapper.HotelMapper;
import com.booking.hotelservice.model.Hotel;
import com.booking.hotelservice.model.Room;
import com.booking.hotelservice.repository.HotelRepository;
import com.booking.hotelservice.repository.RoomRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class HotelService {

  private final HotelRepository hotelRepository;
  private final RoomRepository roomRepository;
  private final HotelMapper hotelMapper;
  private final CloudinaryService cloudinaryService;

  public Page<Hotel> getAllHotels(Pageable pageable) {
    return hotelRepository.findAll(pageable);
  }

  public Optional<Hotel> getHotelById(Long id) {
    return Optional.ofNullable(hotelRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Hotel not found")));
  }

  public List<Room> getRoomByHotelId(Long hotelId) {
    hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

    return roomRepository.findByHotelId(hotelId);
  }

  public Hotel createHotel(HotelDTO hotelDTO, MultipartFile imageHotel) {
    Hotel hotel = hotelMapper.toHotel(hotelDTO);
    hotel.setCreatedAt(java.time.LocalDateTime.now());
    hotel.setUpdatedAt(java.time.LocalDateTime.now());

    if (imageHotel != null && !imageHotel.isEmpty()) {
      try {
        Map data = this.cloudinaryService.upload(imageHotel);
        String imageUrl = (String) data.get("secure_url");
        hotel.setImageUrl(imageUrl);
      } catch (Exception e) {
        throw new RuntimeException("Failed to upload image: " + imageHotel.getOriginalFilename());
      }
    }

    return hotelRepository.save(hotel);
  }

  public Hotel updateHotel(Long id, Hotel updatedHotel) {
    return hotelRepository.findById(id)
        .map(existingHotel -> {
          updateFields(existingHotel, updatedHotel);
          existingHotel.setUpdatedAt(java.time.LocalDateTime.now());
          return hotelRepository.save(existingHotel);
        })
        .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
  }

  private void updateFields(Hotel target, Hotel source) {
    target.setName(source.getName());
    target.setDistrict(source.getDistrict());
    target.setAddressDetail(source.getAddressDetail());
    target.setTotalRooms(source.getTotalRooms());
    target.setStarRating(source.getStarRating());
  }

  public void deleteHotel(Long id) throws ResourceNotFoundException {
    hotelRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

    hotelRepository.deleteById(id);
  }
}
