package com.solita.bikeapp.controller;

import com.solita.bikeapp.entity.JourneyEntity;
import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.repository.JourneyRepository;
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

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
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

    @Test
    void testSave() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/saveAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
        verify(mockReader).readCSV();
        verify(mockService).saveAll();
    }

    @Test
    void testSave_CSVReaderThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockReader).readCSV();

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/saveAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testSave_AppServiceThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockService).saveAll();

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/saveAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
        verify(mockReader).readCSV();
    }

    @Test
    void testGetAllJourneys() throws Exception {
        // Setup
        // Configure AppService.getAllJourneys(...).
        final List<JourneyEntity> journeyEntities = List.of(
                new JourneyEntity(0, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0,
                        "depStationName", 0, "retStationName", 0.0f, 0));
        when(mockService.getAllJourneys()).thenReturn(journeyEntities);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/journeys")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

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
}
