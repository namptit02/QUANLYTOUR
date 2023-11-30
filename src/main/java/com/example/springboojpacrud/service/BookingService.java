package com.example.springboojpacrud.service;

import com.example.springboojpacrud.model.Booking;
import com.example.springboojpacrud.model.User;
import com.example.springboojpacrud.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

}
