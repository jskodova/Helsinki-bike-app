package com.solita.bikeapp.method;

import com.solita.bikeapp.repository.JourneyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CSVReaderTest {
    @Mock
    JourneyRepository journeyRepository;

    @Mock
    List<String> fileNames;

    @Test
    @DisplayName("Should throw an exception when the file has invalid format")
    void readCSVWhenFileHasInvalidFormatThenThrowException() {
        List<String> fileNames = new ArrayList<>();
        fileNames.add("src/main/resources/2021-05.jar");
        CSVReader csvReader = new CSVReader(fileNames, journeyRepository);
        assertThrows(IOException.class, () -> csvReader.readCSV());
    }

    @Test
    @DisplayName("Should throw an exception when the file is not found")
    void readCSVWhenFileIsNotFoundThenThrowException() {
        List<String> fileNames = new ArrayList<>();
        fileNames.add("src/main/resources/2021-05.csv");
        CSVReader csvReader = new CSVReader(fileNames, journeyRepository);
        assertThrows(IOException.class, () -> csvReader.readCSV());
    }

    @Test
    @DisplayName("Should throw an exception when the file is empty")
    void readCSVWhenFileIsEmptyThenThrowException() {
        List<String> fileNames = new ArrayList<>();
        fileNames.add("src/main/resources/csv/empty.csv");
        CSVReader csvReader = new CSVReader(fileNames, journeyRepository);
        assertThrows(IOException.class, () -> csvReader.readCSV());
    }
}