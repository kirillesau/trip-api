package de.kirill.tripapi.web;

import de.kirill.tripapi.model.Trip;
import de.kirill.tripapi.model.TripType;
import de.kirill.tripapi.service.TripService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TripController.class)
@WithMockUser
public class TripControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TripService tripService;

    @Test
    void getTrip() throws Exception {
        given(tripService.getTrip(anyLong())).willReturn(new Trip(1L, "Trip 1", 0, new TripType(1, "test type"), "", "", "", "", null, 0.0, false, new ArrayList<>()));
        mockMvc.perform(get("/trips/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("type.id").value("1"))
                .andExpect(jsonPath("type.name").value("test type"));
    }

    @Test
    void listAllTrips() throws Exception {
        mockMvc.perform(get("/trips"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void listAllTripTypes() throws Exception {
        mockMvc.perform(get("/trip-types"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
