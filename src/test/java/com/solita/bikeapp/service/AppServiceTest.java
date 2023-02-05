package com.solita.bikeapp.service;

import com.solita.bikeapp.controller.JourneyController;
import com.solita.bikeapp.entity.JourneyEntity;
import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.repository.JourneyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("AppService")
class AppServiceTest {

    @Mock
    CSVReader reader;

    @InjectMocks
    JourneyController controller;
    @Mock
    JourneyRepository journeyRepository;
    @Autowired
    AppService appService;

    @BeforeEach
    void setUp() throws IOException {
        appService.saveAll();
    }

    @Test
    @DisplayName("Should return all journeys in the database")
    void getAllJourneysShouldReturnAllJourneysInTheDatabase() {
        Page<JourneyEntity> journeys = appService.getAllJourneys(0, 10);
        assertEquals(10, journeys.getContent().size());
    }

    @Test
    @DisplayName("Should return journeys in the database with pagination")
    void getAllJourneysShouldReturnJourneysInTheDatabaseWithPagination() {
        Page<JourneyEntity> journeys = appService.getAllJourneys(0, 10);
        assertEquals(10, journeys.getSize());
        assertEquals(0, journeys.getNumber());
        assertEquals(10, journeys.getContent().size());
    }

}