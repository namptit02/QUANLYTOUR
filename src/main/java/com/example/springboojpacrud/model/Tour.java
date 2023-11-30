
package com.example.springboojpacrud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Table(name = "Tour")
@Entity
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tourName;
    private String tourSchedule;
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourCheckIn> tourCheckIns;
    @Column
    private int timeOfStay;

    @Column
    private String transport;

    @Column
    private double priceTicket;

    @OneToMany(mappedBy = "tour")
    private List<TourPlace> tourPlaces;

}