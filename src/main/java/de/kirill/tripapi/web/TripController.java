package de.kirill.tripapi.web;

import de.kirill.tripapi.Trip;
import de.kirill.tripapi.TripType;
import de.kirill.tripapi.service.TripService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/trips/{id}")
    @ResponseStatus(OK)
    public Trip getTrip(@PathVariable long id) {
        return tripService.getTrip(id);
    }

    @GetMapping("/trips")
    @ResponseStatus(OK)
    public List<Trip> getTrip() {
        return tripService.getAllTrips();
    }
    @GetMapping("/trip-types")
    @ResponseStatus(OK)
    public List<TripType> getTripTypes() {
        return tripService.getAllTripTypes();
    }

}
