package de.kirill.tripapi.service;

import de.kirill.tripapi.model.Trip;
import de.kirill.tripapi.model.TripBooking;
import de.kirill.tripapi.model.TripType;
import de.kirill.tripapi.model.repository.TripBookingRepository;
import de.kirill.tripapi.model.repository.TripRepository;
import de.kirill.tripapi.model.repository.TripTypeRepository;
import de.kirill.tripapi.web.exception.TripNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TripService {

    private TripRepository tripRepository;
    private TripTypeRepository tripTypeRepository;
    private TripBookingRepository tripBookingRepository;

    public Trip getTrip(long id) {
        return tripRepository.findById(id).orElseThrow(() -> new TripNotFoundException(id));
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public List<TripType> getAllTripTypes() {
        return tripTypeRepository.findAll();
    }

    @Transactional
    public TripBooking createTripBooking(long tripId, TripBooking booking) {
        booking.setTrip(getTrip(tripId));
        return tripBookingRepository.save(booking);
    }

    @Transactional
    public void deleteTripBooking(long tripId, long bookingId) {
        tripBookingRepository.deleteByIdAndTripId(bookingId, tripId);
    }
}
