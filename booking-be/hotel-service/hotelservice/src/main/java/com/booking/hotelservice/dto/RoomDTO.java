package com.booking.hotelservice.dto;

import com.booking.hotelservice.enums.TypeRoom;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class RoomDTO {

  @NotNull(message = "Loại phòng không được để trống")
  private TypeRoom typeRoom;

  @Min(value = 1, message = "Sức chứa tối thiểu là 1 khách")
  private int capacity;

  @DecimalMin(value = "0.0", inclusive = false, message = "Giá mỗi đêm phải lớn hơn 0")
  private double pricePerNight;

  private boolean available;

  @NotNull(message = "HotelId không được để trống")
  private Long hotelId;

  private List<String> listImageUrl = new ArrayList<>();}