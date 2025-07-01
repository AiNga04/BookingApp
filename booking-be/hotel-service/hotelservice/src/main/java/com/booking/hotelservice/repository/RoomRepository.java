package com.booking.hotelservice.repository;

import com.booking.hotelservice.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
