package com.booking.hotelservice.mapper;

import com.booking.hotelservice.dto.HotelDTO;
import com.booking.hotelservice.model.Hotel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

  @Autowired
  private ModelMapper modelMapper;

  public Hotel toHotel(HotelDTO dto) {

    Hotel hotel = modelMapper.map(dto, Hotel.class);
    return hotel;
  }

  public HotelDTO toDTO(Hotel hotel) {
    return modelMapper.map(hotel, HotelDTO.class);
  }

}
