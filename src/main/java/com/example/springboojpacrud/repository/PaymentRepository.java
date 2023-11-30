package com.example.springboojpacrud.repository;

import com.example.springboojpacrud.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
