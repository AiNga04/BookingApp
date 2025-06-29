package com.booking.hotelservice.controller;

import com.booking.hotelservice.dto.HotelDTO;
import com.booking.hotelservice.dto.ResponseSuccess;
import com.booking.hotelservice.mapper.HotelMapper;
import com.booking.hotelservice.model.Hotel;
import com.booking.hotelservice.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
public class HotelController {

  private final HotelService hotelService;
  private final HotelMapper hotelMapper;

  @GetMapping
  public ResponseSuccess getAllHotels(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "id") String sort) {

    Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
    Page<HotelDTO> hotelPage = hotelService.getAllHotels(pageable)
        .map(hotelMapper::toDTO);

    return new ResponseSuccess(HttpStatus.OK, "Fetched hotel list successfully", hotelPage);
  }

  @GetMapping("/{id}")
  public ResponseSuccess getHotelById(@PathVariable Long id) {
    return hotelService.getHotelById(id)
        .map(hotelMapper::toDTO)
        .map(dto -> new ResponseSuccess(HttpStatus.OK, "Hotel found", dto))
        .orElseThrow(() -> new RuntimeException("Hotel not found"));
  }

  @PostMapping
  public ResponseSuccess createHotel(@Valid @RequestBody HotelDTO hotel) {
    Hotel created = hotelService.createHotel(hotel);
    return new ResponseSuccess(HttpStatus.CREATED, "Hotel created successfully", created);
  }

  @PutMapping("/{id}")
  public ResponseSuccess updateHotel(@PathVariable Long id, @Valid @RequestBody Hotel hotel) {
    Hotel updated = hotelService.updateHotel(id, hotel);
    return new ResponseSuccess(HttpStatus.OK, "Hotel updated successfully", updated);
  }

  @DeleteMapping("/{id}")
  public ResponseSuccess deleteHotel(@PathVariable Long id) {
    hotelService.deleteHotel(id);
    return new ResponseSuccess(HttpStatus.OK, "Hotel deleted successfully");
  }
}
