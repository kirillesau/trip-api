package de.kirill.tripapi.web;

import de.kirill.tripapi.Trip;
import de.kirill.tripapi.TripType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Sql(executionPhase = BEFORE_TEST_CLASS, scripts = "classpath:testdata/TripControllerTestData.sql")
public class TripControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

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

    private TestRestTemplate authRestTemplate() {
        return restTemplate.withBasicAuth("admin", "admin");
    }
}