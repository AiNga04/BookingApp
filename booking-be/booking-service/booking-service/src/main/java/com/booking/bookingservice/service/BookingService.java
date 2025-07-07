package com.booking.bookingservice.service;

import com.booking.bookingservice.dto.request.BookingRequest;
import com.booking.bookingservice.dto.response.BookingResponse;
import com.booking.bookingservice.exception.ResourceNotFoundException;
import com.booking.bookingservice.mapper.BookingMapper;
import com.booking.bookingservice.model.Booking;
import com.booking.bookingservice.repository.BookingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {

  BookingRepository bookingRepository;
  BookingMapper bookingMapper;

  public BookingResponse createBooking(BookingRequest request) {
    Booking saved = bookingRepository.save(bookingMapper.toBooking(request));
    return bookingMapper.toBookingResponse(saved);
  }

  public Page<BookingResponse> getAllBookings(Pageable pageable) {
    return bookingRepository.findAll(pageable)
        .map(bookingMapper::toBookingResponse);
  }


  public BookingResponse getBookingById(Long id) {
    Booking booking = bookingRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
    return bookingMapper.toBookingResponse(booking);
  }

  public BookingResponse updateBooking(Long id, BookingRequest request) {
    Booking booking = bookingRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

    bookingMapper.toBooking(booking, request);

    Booking updated = bookingRepository.save(booking);
    return bookingMapper.toBookingResponse(updated);
  }

  public void deleteBooking(Long id) {
    if (!bookingRepository.existsById(id)) {
      throw new ResourceNotFoundException("Booking not found with id: " + id);
    }
    bookingRepository.deleteById(id);
  }

}
