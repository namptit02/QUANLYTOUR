package com.example.springboojpacrud.repository;

import com.example.springboojpacrud.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Integer> {
    List<Tour> findByTourNameContaining(String keyword);

    @Query(value = "SELECT t FROM Tour t ORDER BY RAND() LIMIT 10")
    List<Tour> findRandom10Tours();
}
