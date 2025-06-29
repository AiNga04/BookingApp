package com.booking.hotelservice.mapper;

import com.booking.hotelservice.dto.HotelDTO;
import com.booking.hotelservice.model.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

  public Hotel toHotel(HotelDTO dto) {
    return Hotel.builder()
        .name(dto.getName())
        .district(dto.getDistrict())
        .addressDetail(dto.getAddressDetail())
        .totalRooms(dto.getTotalRooms())
        .starRating(dto.getStarRating())
        .isDeleted(false)
        .userId(dto.getUserId())
        .build();
  }

  public HotelDTO toDTO(Hotel hotel) {
    HotelDTO dto = new HotelDTO();
    dto.setId(hotel.getId());
    dto.setName(hotel.getName());
    dto.setDistrict(hotel.getDistrict());
    dto.setAddressDetail(hotel.getAddressDetail());
    dto.setTotalRooms(hotel.getTotalRooms());
    dto.setStarRating(hotel.getStarRating());
    dto.setUserId(hotel.getUserId());
    return dto;
  }

}
