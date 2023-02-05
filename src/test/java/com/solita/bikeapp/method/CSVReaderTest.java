package com.solita.bikeapp.method;

import com.solita.bikeapp.entity.JourneyEntity;
import com.solita.bikeapp.repository.JourneyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CSVReaderTest {

    @Mock
    private JourneyRepository mockJourneyRepository;

    private CSVReader csvReaderUnderTest;

    @BeforeEach
    void setUp() {
        csvReaderUnderTest = new CSVReader(List.of("value"), mockJourneyRepository);
    }

    @Test
    void testReadCSV() throws Exception {
        // Setup
        // Configure JourneyRepository.save(...).
        final JourneyEntity journeyEntity = new JourneyEntity(0, LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0, "depStationName", 0, "retStationName", 0.0f, 0);
        when(mockJourneyRepository.save(
                new JourneyEntity(0, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0,
                        "depStationName", 0, "retStationName", 0.0f, 0))).thenReturn(journeyEntity);

        // Run the test
        csvReaderUnderTest.readCSV();

        // Verify the results
        verify(mockJourneyRepository).save(
                new JourneyEntity(0, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0,
                        "depStationName", 0, "retStationName", 0.0f, 0));
    }

    @Test
    void testReadCSV_JourneyRepositoryThrowsOptimisticLockingFailureException() {
        // Setup
        when(mockJourneyRepository.save(
                new JourneyEntity(0, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0,
                        "depStationName", 0, "retStationName", 0.0f, 0)))
                .thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThrows(OptimisticLockingFailureException.class, () -> csvReaderUnderTest.readCSV());
    }
}
