package com.solita.bikeapp.service;

import com.solita.bikeapp.entity.JourneyEntity;
import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.repository.JourneyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AppServiceTest {

    private AppService appServiceUnderTest;

    @BeforeEach
    void setUp() {
        appServiceUnderTest = new AppService();
        appServiceUnderTest.journeyRepository = mock(JourneyRepository.class);
        appServiceUnderTest.reader = mock(CSVReader.class);
    }

    @Test
    void testSaveAll_CSVReaderThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(appServiceUnderTest.reader).readCSV();

        // Run the test
        assertThrows(IOException.class, () -> appServiceUnderTest.saveAll());
    }

    @Test
    void testGetAllJourneys() {
        // Setup
        final List<JourneyEntity> expectedResult = List.of(
                new JourneyEntity(0, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0,
                        "depStationName", 0, "retStationName", 0.0f, 0));

        // Configure JourneyRepository.findAll(...).
        final List<JourneyEntity> journeyEntities = List.of(
                new JourneyEntity(0, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0,
                        "depStationName", 0, "retStationName", 0.0f, 0));
        when(appServiceUnderTest.journeyRepository.findAll()).thenReturn(journeyEntities);

        // Run the test
        final List<JourneyEntity> result = appServiceUnderTest.getAllJourneys();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetAllJourneys_JourneyRepositoryReturnsNoItems() {
        // Setup
        when(appServiceUnderTest.journeyRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<JourneyEntity> result = appServiceUnderTest.getAllJourneys();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}