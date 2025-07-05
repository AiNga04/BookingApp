package com.booking.bookingservice.dto.request;

import com.booking.bookingservice.enums.BookingStatus;
import com.booking.bookingservice.enums.PaymentType;
import com.booking.bookingservice.validation.EnumValidator;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import lombok.Data;

@Data
public class BookingRequest {

  @NotNull(message = "Customer ID must not be null")
  private Long customerId;

  @NotNull(message = "Hotel ID must not be null")
  private Long hotelId;

  @NotNull(message = "Room ID must not be null")
  private Long roomId;

  @NotNull(message = "Check-in date must not be null")
  @Future(message = "Check-in date must be in the future")
  private LocalDate checkInDate;

  @NotNull(message = "Check-out date must not be null")
  @Future(message = "Check-out date must be in the future")
  private LocalDate checkOutDate;

  @NotNull(message = "Total price must not be null")
  @Positive(message = "Total price must be greater than 0")
  private Double totalPrice;

  @NotNull(message = "Payment type must not be null")
  private PaymentType paymentType;

  @NotNull(message = "Status must not be null")
  @EnumValidator(enumClass = BookingStatus.class, ignoreCase = true, message = "Invalid booking status")
  private BookingStatus status;

  private String notes;
}
