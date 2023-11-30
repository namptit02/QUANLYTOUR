package com.example.springboojpacrud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "Tour_Place")
@Entity
public class TourPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
    @OneToMany(mappedBy = "tourPlace")
    private List<SupplierTourPlace> supplierTourPlaces;
    // Các thuộc tính khác của TourPlace và các getter, setter tương ứng
}