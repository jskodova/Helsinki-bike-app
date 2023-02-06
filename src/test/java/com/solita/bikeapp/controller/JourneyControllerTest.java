package com.solita.bikeapp.controller;

import com.solita.bikeapp.entity.JourneyEntity;
import com.solita.bikeapp.entity.StationEntity;
import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.repository.JourneyRepository;
import com.solita.bikeapp.repository.StationRepository;
import com.solita.bikeapp.service.AppService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
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

    @Mock
    CSVReader reader;
    @Mock
    AppService service;
    @Mock
    JourneyRepository repository;
    @Mock
    StationRepository stationRepository;

    @InjectMocks
    JourneyController controller;

    @Test
    void getAllJourneys() {
        List<JourneyEntity> journeyList = new ArrayList<>();
        when(service.getAllJourneys()).thenReturn(journeyList);
        controller.getAllJourneys();
        verify(service, times(1)).getAllJourneys();
        assertEquals(journeyList, controller.getAllJourneys());
    }

    @Test
    void getAllStations() {
        List<StationEntity> stationList = new ArrayList<>();
        when(service.getAllStations()).thenReturn(stationList);
        controller.getAllStations();
        verify(service, times(1)).getAllStations();
        assertEquals(stationList, controller.getAllStations());
    }

    @Test
    void getJourneyByID() {
        JourneyEntity journey = new JourneyEntity();
        when(repository.findById(1L)).thenReturn(Optional.of(journey));
        controller.getJourneyByID(1L);
        verify(repository, times(1)).findById(1L);
        assertEquals(Optional.of(journey), controller.getJourneyByID(1L));
    }

    @Test
    void getStationByID() {
        StationEntity station = new StationEntity();
        when(stationRepository.findById(1L)).thenReturn(Optional.of(station));
        controller.getStationByID(1L);
        verify(stationRepository, times(1)).findById(1L);
        assertEquals(Optional.of(station), controller.getStationByID(1L));
    }

    @Test
    void createJourney() {
        JourneyEntity journey = new JourneyEntity();
        when(repository.save(journey)).thenReturn(journey);
        controller.createJourney(journey);
        verify(repository, times(1)).save(journey);
        assertEquals(journey, controller.createJourney(journey));
    }

    @Test
    void createStation() {
        StationEntity station = new StationEntity();
        when(stationRepository.save(station)).thenReturn(station);
        controller.createStation(station);
        verify(stationRepository, times(1)).save(station);
        assertEquals(station, controller.createStation(station));
    }
}

