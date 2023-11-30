package com.example.springboojpacrud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class BookedTour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    private int numberOfTickets;
    private double totalPrice;

    // Getters and setters
}