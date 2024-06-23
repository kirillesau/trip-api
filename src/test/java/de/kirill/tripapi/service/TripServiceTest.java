package de.kirill.tripapi.service;

import de.kirill.tripapi.web.exception.TripNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class TripServiceTest {

    @Autowired
    TripService underTest;

    @Test
    void unknownTripShouldThrowException() {
        assertThatThrownBy(() -> underTest.getTrip(1))
                .isInstanceOf(TripNotFoundException.class)
                .hasMessage("Trip not found with id: 1");
    }
}