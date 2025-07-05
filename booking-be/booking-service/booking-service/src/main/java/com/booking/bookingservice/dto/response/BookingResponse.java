package com.booking.bookingservice.dto.response;

import com.booking.bookingservice.enums.BookingStatus;
import com.booking.bookingservice.enums.PaymentType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BookingResponse {
  private Long id;
  private String bookingCode;
  private Long customerId;
  private Long hotelId;
  private Long roomId;
  private LocalDate checkInDate;
  private LocalDate checkOutDate;
  private Double totalPrice;
  private BookingStatus status;
  private PaymentType paymentType;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String notes;
}
