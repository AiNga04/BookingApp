package com.booking.hotelservice.mapper;

import com.booking.hotelservice.dto.RoomDTO;
import com.booking.hotelservice.exception.ResourceNotFoundException;
import com.booking.hotelservice.model.Hotel;
import com.booking.hotelservice.model.Room;
import com.booking.hotelservice.repository.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

  @Autowired
  HotelRepository hotelRepository;

  @Autowired
  private ModelMapper modelMapper;

  public Room toRoom(RoomDTO roomDTO) {

    Hotel hotel = hotelRepository.findById(roomDTO.getHotelId()).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

    Room room = modelMapper.map(roomDTO, Room.class);

    room.setHotel(hotel);
    return room;
  }
}
