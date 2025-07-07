package com.booking.bookingservice.mapper;

import com.booking.bookingservice.dto.request.BookingRequest;
import com.booking.bookingservice.dto.response.BookingResponse;
import com.booking.bookingservice.enums.BookingStatus;
import com.booking.bookingservice.model.Booking;
import java.time.LocalDateTime;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

  @Autowired
  private ModelMapper modelMapper;

  public BookingResponse toBookingResponse(Booking booking) {
    return modelMapper.map(booking, BookingResponse.class);
  }

  public Booking toBooking(BookingRequest bookingRequest) {
    Booking booking = new Booking();
    booking.setCustomerId(bookingRequest.getCustomerId());
    booking.setHotelId(bookingRequest.getHotelId());
    booking.setRoomId(bookingRequest.getRoomId());
    booking.setCheckInDate(bookingRequest.getCheckInDate());
    booking.setCheckOutDate(bookingRequest.getCheckOutDate());
    booking.setTotalPrice(bookingRequest.getTotalPrice());
    booking.setPaymentType(bookingRequest.getPaymentType());
    booking.setStatus(BookingStatus.PENDING);
    booking.setNotes(bookingRequest.getNotes());
    booking.setBookingCode("BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
    booking.setCreatedAt(LocalDateTime.now());
    booking.setUpdatedAt(LocalDateTime.now());
    return booking;
  }

  public void toBooking(Booking booking, BookingRequest bookingRequest) {
    if (booking == null || bookingRequest == null) {
      throw new IllegalArgumentException("Booking and BookingRequest must not be null");
    }
    booking.setCustomerId(bookingRequest.getCustomerId());
    booking.setHotelId(bookingRequest.getHotelId());
    booking.setRoomId(bookingRequest.getRoomId());
    booking.setCheckInDate(bookingRequest.getCheckInDate());
    booking.setCheckOutDate(bookingRequest.getCheckOutDate());
    booking.setTotalPrice(bookingRequest.getTotalPrice());
    booking.setPaymentType(bookingRequest.getPaymentType());
    booking.setNotes(bookingRequest.getNotes());
    booking.setStatus(bookingRequest.getStatus());
    booking.setUpdatedAt(LocalDateTime.now());
  }
}
