package com.example.springboojpacrud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "Supplier")
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String supplierName;

    @OneToMany(mappedBy = "supplier")
    private List<SupplierTourPlace> supplierTourPlaces;

    // Các thuộc tính khác của Supplier và các getter, setter tương ứng
}