package com.booking.hotelservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HotelDTO {
  private Long id;

  @NotBlank(message = "Tên khách sạn không được để trống")
  private String name;

  @NotBlank(message = "Quận không được để trống")
  private String district;

  @NotBlank(message = "Địa chỉ không được để trống")
  private String addressDetail;

  @Min(value = 1, message = "Số phòng tối thiểu là 1")
  private int totalRooms;

  @DecimalMin(value = "0.0", message = "Sao đánh giá phải >= 0")
  private double starRating;

  private Long userId;
}
