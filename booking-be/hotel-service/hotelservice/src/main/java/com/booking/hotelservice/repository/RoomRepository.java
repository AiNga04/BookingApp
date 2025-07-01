package com.booking.hotelservice.repository;

import com.booking.hotelservice.model.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

  List<Room> findByHotelId(Long hotelId);
}
