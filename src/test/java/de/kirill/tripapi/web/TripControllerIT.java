package de.kirill.tripapi.web;

import de.kirill.tripapi.model.Trip;
import de.kirill.tripapi.model.TripBooking;
import de.kirill.tripapi.model.TripType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;
import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpEntity.EMPTY;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Sql(executionPhase = BEFORE_TEST_CLASS, scripts = "classpath:schema.sql")
@Sql(executionPhase = BEFORE_TEST_CLASS, scripts = "classpath:testdata/TripControllerTestData.sql")
@Testcontainers
public class TripControllerIT {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("integration-tests-db")
            .withUsername("username")
            .withPassword("password");

    static {
        postgreSQLContainer.start();
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    public void getTripReturnsTrip() {
        var response = authRestTemplate().getForEntity("/trips/1", Trip.class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1L);
    }

    @Test
    public void getTripsReturnsListOfTrips() {
        var response = authRestTemplate().getForEntity("/trips", Trip[].class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void getTripTypesReturnsListOfTripTypes() {
        var response = authRestTemplate().getForEntity("/trip-types", TripType[].class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void bookingsCanBeCreatedAndDeleted() {
        // Anzahl der Buchungen muss 2 sein
        var tripResponse = authRestTemplate().getForEntity("/trips/1", Trip.class);
        assertThat(tripResponse.getBody()).isNotNull();
        assertThat(tripResponse.getBody().getBookings()).hasSize(2);

        // Füge eine Buchung hinzu
        var newBooking = new HttpEntity<>(TripBooking.of(Date.valueOf("2021-12-24")));
        var bookingResponse = authRestTemplate().exchange("/trips/1/booking", PUT, newBooking, Void.class);
        assertThat(bookingResponse.getStatusCode()).isEqualTo(CREATED);
        URI newBookingLocation = bookingResponse.getHeaders().getLocation();

        // Anzahl der Buchungen hat sich um 1 erhöht
        tripResponse = authRestTemplate().getForEntity("/trips/1", Trip.class);
        assertThat(tripResponse.getBody()).isNotNull();
        assertThat(tripResponse.getBody().getBookings()).hasSize(3);

        // Lösche die letzte Buchung
        ResponseEntity<Void> deleteResponse = authRestTemplate().exchange(newBookingLocation, DELETE, EMPTY, Void.class);
        assertThat(deleteResponse.getStatusCode()).isEqualTo(ACCEPTED);

        // Anzahl der Buchungen muss 2 sein
        tripResponse = authRestTemplate().getForEntity("/trips/1", Trip.class);
        assertThat(tripResponse.getBody()).isNotNull();
        assertThat(tripResponse.getBody().getBookings()).hasSize(2);
    }

    private TestRestTemplate authRestTemplate() {
        return restTemplate.withBasicAuth("admin", "admin");
    }
}