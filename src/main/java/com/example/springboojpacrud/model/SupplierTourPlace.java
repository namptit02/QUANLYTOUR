package com.example.springboojpacrud.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "Supplier_TourPlace")
@Entity
public class SupplierTourPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "tour_place_id")
    private TourPlace tourPlace;

    // Các thuộc tính khác của SupplierTourPlace và các getter, setter tương ứng
}
