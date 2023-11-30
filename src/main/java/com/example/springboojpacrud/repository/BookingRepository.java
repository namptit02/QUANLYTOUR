package com.example.springboojpacrud.repository;

import com.example.springboojpacrud.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
}
