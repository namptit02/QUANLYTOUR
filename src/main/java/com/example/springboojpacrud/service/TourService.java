package com.example.springboojpacrud.service;

import com.example.springboojpacrud.model.Tour;
import com.example.springboojpacrud.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {
    @Autowired
    TourRepository tourRepository;

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }
    public List<Tour> getRandom10Tours() {
        return tourRepository.findRandom10Tours();
    }
    public List<Tour> searchTours(String keyword) {
        return tourRepository.findByTourNameContaining(keyword);
    }

    public Tour getTourById(int id) {
        Optional<Tour> optionalTour = tourRepository.findById(id);
        return optionalTour.orElse(null);
    }


    public Tour saveTour(Tour tour) {
        return tourRepository.save(tour);
    }


    public void deleteTour(int id) {
        tourRepository.deleteById(id);
    }
}
