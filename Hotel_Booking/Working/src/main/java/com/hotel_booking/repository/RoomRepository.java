package com.hotel_booking.repository;

import com.hotel_booking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    // This is used for getting the distinct roomtypes from the database to the frontend
    @Query("SELECT DISTINCT r.roomType FROM Room r WHERE r.roomType IS NOT NULL AND r.roomType <> ''")
    List<String> findDistinctRoomTypes();

    @Query("SELECT r FROM Room r " +
    "WHERE r.roomType LIKE %:roomType% " +
    "AND r.id NOT IN (" +
    " SELECT br.room.id FROM BookedRoom br " +
    " WHERE ((br.checkInDate <= :checkOutDate) AND (br.checkOutDate >= :checkInDate))" +
    ")")
    List<Room> findAvailableRoomsByDatesAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType);
}
