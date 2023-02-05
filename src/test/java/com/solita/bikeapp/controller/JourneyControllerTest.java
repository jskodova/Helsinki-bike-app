package com.solita.bikeapp.controller;

import com.solita.bikeapp.entity.JourneyEntity;
import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.repository.JourneyRepository;
import com.solita.bikeapp.repository.StationRepository;
import com.solita.bikeapp.service.AppService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JourneyControllerTest {
    @Mock
    AppService service;
    @Mock
    CSVReader reader;

    @InjectMocks
    JourneyController controller;
    @Mock
    JourneyRepository repository;
    @Mock
    StationRepository stationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should return all journeys when the page and size are not specified")
    void getAllJourneysWhenPageAndSizeAreNotSpecified() {
        Page<JourneyEntity> journeys =
                new PageImpl<>(Collections.singletonList(new JourneyEntity()));
        when(service.getAllJourneys(0, 10)).thenReturn(journeys);
        assertEquals(journeys, controller.getAllJourneys(0, 10));
    }

    @Test
    @DisplayName("Should return journeys in the page when the page is specified")
    void getAllJourneysWhenPageIsSpecified() {
        Page<JourneyEntity> journeys =
                new PageImpl<>(Collections.singletonList(new JourneyEntity()));
        when(service.getAllJourneys(1, 10)).thenReturn(journeys);
        assertEquals(journeys, controller.getAllJourneys(1, 10));
    }
}