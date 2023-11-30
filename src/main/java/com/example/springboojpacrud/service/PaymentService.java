package com.example.springboojpacrud.service;

import com.example.springboojpacrud.model.Payment;
import com.example.springboojpacrud.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    public void getPayment(long bookingId) {
        paymentRepository.findById(bookingId);
    }
}
