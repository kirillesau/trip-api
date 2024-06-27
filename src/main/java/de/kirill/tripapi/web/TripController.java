package de.kirill.tripapi.web;

import de.kirill.tripapi.Trip;
import de.kirill.tripapi.TripBooking;
import de.kirill.tripapi.TripType;
import de.kirill.tripapi.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

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

    @PutMapping("/trips/{tripId}/booking")
    @ResponseStatus(CREATED)
    public ResponseEntity<Void> addTripBooking(@PathVariable long tripId, @RequestBody TripBooking booking) {
        var tripBooking = tripService.createTripBooking(tripId, booking);
        return entityWithLocation(tripBooking.getId());
    }

    @DeleteMapping("/trips/{tripId}/booking/{bookingId}")
    @ResponseStatus(ACCEPTED)
    public void deleteTripBooking(@PathVariable long tripId, @PathVariable long bookingId) {
        tripService.deleteTripBooking(tripId, bookingId);
    }

    @GetMapping("/trip-types")
    @ResponseStatus(OK)
    public List<TripType> getTripTypes() {
        return tripService.getAllTripTypes();
    }

    private ResponseEntity<Void> entityWithLocation(Object id) {
        URI location = fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }
}
