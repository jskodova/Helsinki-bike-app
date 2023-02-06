package com.solita.bikeapp.service;

import com.solita.bikeapp.entity.JourneyEntity;
import com.solita.bikeapp.entity.StationEntity;
import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.repository.JourneyRepository;
import com.solita.bikeapp.repository.StationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppServiceTest {

    @Mock
    private JourneyRepository mockJourneyRepository;
    @Mock
    private CSVReader mockReader;
    @Mock
    private StationRepository mockStationRepository;

    @InjectMocks
    private AppService appServiceUnderTest;

    @Test
    void testSaveAll_CSVReaderThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockReader).readCSV();

        // Run the test
        assertThrows(IOException.class, () -> appServiceUnderTest.saveAll());
    }

    @Test
    void testSaveAllStations_CSVReaderThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockReader).readAddress();

        // Run the test
        assertThrows(IOException.class, () -> appServiceUnderTest.saveAllStations());
    }

    @Test
    void testGetAllJourneys() {
        // Setup
        final JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setJourneyID(0);
        journeyEntity.setDepartureTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        journeyEntity.setReturnTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        journeyEntity.setDepStationID(0);
        journeyEntity.setDepStationName("depStationName");
        journeyEntity.setRetStationID(0);
        journeyEntity.setRetStationName("retStationName");
        journeyEntity.setDistance(0.0f);
        journeyEntity.setDuration(0);
        final List<JourneyEntity> expectedResult = List.of(journeyEntity);

        // Configure JourneyRepository.findAll(...).
        final JourneyEntity journeyEntity1 = new JourneyEntity();
        journeyEntity1.setJourneyID(0);
        journeyEntity1.setDepartureTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        journeyEntity1.setReturnTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        journeyEntity1.setDepStationID(0);
        journeyEntity1.setDepStationName("depStationName");
        journeyEntity1.setRetStationID(0);
        journeyEntity1.setRetStationName("retStationName");
        journeyEntity1.setDistance(0.0f);
        journeyEntity1.setDuration(0);
        final List<JourneyEntity> journeyEntities = List.of(journeyEntity1);
        when(mockJourneyRepository.findAll()).thenReturn(journeyEntities);

        // Run the test
        final List<JourneyEntity> result = appServiceUnderTest.getAllJourneys();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetAllJourneys_JourneyRepositoryReturnsNoItems() {
        // Setup
        when(mockJourneyRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<JourneyEntity> result = appServiceUnderTest.getAllJourneys();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetAllStations() {
        // Setup
        // Configure StationRepository.findAll(...).
        final List<StationEntity> stationEntities = List.of(
                new StationEntity(0, "stationName", "address", "city", 0.0f, 0.0f, 0, 0, 0.0, 0.0));
        when(mockStationRepository.findAll()).thenReturn(stationEntities);

        // Run the test
        final List<StationEntity> result = appServiceUnderTest.getAllStations();

        // Verify the results
    }

    @Test
    void testGetAllStations_StationRepositoryReturnsNoItems() {
        // Setup
        when(mockStationRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<StationEntity> result = appServiceUnderTest.getAllStations();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
