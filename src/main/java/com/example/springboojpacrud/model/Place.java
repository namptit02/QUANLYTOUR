package com.example.springboojpacrud.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "Place")
@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String placeName;

    @OneToMany(mappedBy = "place")
    private List<TourPlace> tourPlaces;

    // Các thuộc tính khác của Place và các getter, setter tương ứng
}