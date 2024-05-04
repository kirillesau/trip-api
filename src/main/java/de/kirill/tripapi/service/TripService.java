package de.kirill.tripapi.service;

import de.kirill.tripapi.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    public Trip getTrip(long id) {
        Trip trip = new Trip();
        trip.setId(id);
        return trip;
    }

    public List<Trip> getAllTrips() {
        var trip1 = new Trip();
        trip1.setId(1L);
        var trip2 = new Trip();
        trip2.setId(2L);
        return List.of(trip1, trip2);
    }
}
