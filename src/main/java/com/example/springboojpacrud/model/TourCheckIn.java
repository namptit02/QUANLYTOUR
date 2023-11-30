package com.example.springboojpacrud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "TourCheckIn")
@Entity
public class TourCheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    private Date startDate;

    // Các thuộc tính và phương thức khác của TourCheckIn
}