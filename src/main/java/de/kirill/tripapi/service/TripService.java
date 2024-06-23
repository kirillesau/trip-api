package de.kirill.tripapi.service;

import de.kirill.tripapi.Trip;
import de.kirill.tripapi.TripRepository;
import de.kirill.tripapi.TripTypeRepository;
import de.kirill.tripapi.web.exception.TripNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TripService {

    private TripRepository tripRepository;
    private TripTypeRepository tripTypeRepository;

    public Trip getTrip(long id) {
        return tripRepository.findById(id).orElseThrow(() -> new TripNotFoundException(id));
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public List<Trip> getAllTripTypes() {
        return tripRepository.findAll();
    }
}
