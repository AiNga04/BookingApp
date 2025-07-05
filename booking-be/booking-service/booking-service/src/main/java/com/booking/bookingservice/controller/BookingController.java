package com.booking.bookingservice.controller;

import com.booking.bookingservice.dto.request.BookingRequest;
import com.booking.bookingservice.dto.response.BookingResponse;
import com.booking.bookingservice.dto.response.PagedResponse;
import com.booking.bookingservice.dto.response.ResponseSuccess;
import com.booking.bookingservice.service.BookingService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {

  BookingService bookingService;

  @PostMapping
  public ResponseSuccess createBooking(@Valid @RequestBody BookingRequest request) {
    BookingResponse response = bookingService.createBooking(request);
    return new ResponseSuccess(HttpStatus.CREATED, "Booking created successfully", response);
  }

  @GetMapping
  public ResponseSuccess getAllBookings(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size);

    Page<BookingResponse> bookings = bookingService.getAllBookings(pageable);
    PagedResponse<BookingResponse> pagedResponse = new PagedResponse<>(
        bookings.getContent(),
        bookings.getNumber(),
        bookings.getSize(),
        bookings.getTotalElements(),
        bookings.getTotalPages(),
        bookings.isLast()
    );
    return new ResponseSuccess(HttpStatus.OK, "Bookings retrieved successfully", pagedResponse);
  }

  @GetMapping("/{id}")
  public ResponseSuccess getBookingById(@PathVariable Long id) {
    BookingResponse response = bookingService.getBookingById(id);
    return new ResponseSuccess(HttpStatus.OK, "Booking retrieved successfully", response);
  }

  @PutMapping("/{id}")
  public ResponseSuccess updateBooking(@PathVariable Long id,
      @Valid @RequestBody BookingRequest request) {
    BookingResponse response = bookingService.updateBooking(id, request);
    return new ResponseSuccess(HttpStatus.OK, "Booking updated successfully", response);
  }

  @DeleteMapping("/{id}")
  public ResponseSuccess deleteBooking(@PathVariable Long id) {
    bookingService.deleteBooking(id);
    return new ResponseSuccess(HttpStatus.NO_CONTENT, "Booking deleted successfully");
  }
}
