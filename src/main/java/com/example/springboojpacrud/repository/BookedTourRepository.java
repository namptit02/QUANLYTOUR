package com.example.springboojpacrud.repository;

import com.example.springboojpacrud.model.BookedTour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedTourRepository extends JpaRepository<BookedTour, Integer> {

}
