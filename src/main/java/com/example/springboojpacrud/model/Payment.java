package com.example.springboojpacrud.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Payment {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private String paymentType;
    private String bankName;
    private String cardNumber;

    // Getters and setters
}