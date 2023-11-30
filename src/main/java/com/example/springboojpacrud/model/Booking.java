package com.example.springboojpacrud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "Booking")
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Column
    private Date startDate;

    @Column
    private int numberOfTickets;

    @Column
    private double totalPrice;

    // Các thuộc tính khác của Booking và các getter, setter tương ứng
}