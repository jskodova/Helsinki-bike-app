package com.solita.bikeapp.controller;

import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.repository.JourneyRepository;
import com.solita.bikeapp.repository.StationRepository;
import com.solita.bikeapp.service.AppService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(JourneyController.class)
class JourneyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CSVReader mockReader;
    @MockBean
    private AppService mockService;
    @MockBean
    private JourneyRepository mockRepository;
    @MockBean
    private StationRepository mockStationRepository;

    @Test
    void testGetAllJourneys_AppServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockService.getAllJourneys()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/journeys")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("[]", response.getContentAsString());
    }

    @Test
    void testGetAllStations_AppServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockService.getAllStations()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/stations")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("[]", response.getContentAsString());
    }
}

