package de.kirill.tripapi.model.repository;

import de.kirill.tripapi.model.TripBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TripBookingRepository extends JpaRepository<TripBooking, Long> {
    void deleteByIdAndTripId(@Param("id") long id, @Param("tripId") long tripId);
}
