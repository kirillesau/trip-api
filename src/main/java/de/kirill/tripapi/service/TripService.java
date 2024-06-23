package de.kirill.tripapi.service;

import de.kirill.tripapi.Trip;
import de.kirill.tripapi.TripRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TripService {

    private TripRepository tripRepository;

    public Trip getTrip(long id) {
        return tripRepository.findById(id).orElseThrow(() -> new RuntimeException("Trip not found"));
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }
}
