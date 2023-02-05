package com.solita.bikeapp.service;

import com.solita.bikeapp.entity.JourneyEntity;
import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AppService {
    @Autowired
    JourneyRepository journeyRepository;
    @Autowired
    CSVReader reader;

    public void saveAll() throws IOException {
        reader.readCSV();
        journeyRepository.saveAll(CSVReader.journeys);
    }

    public Page<JourneyEntity> getAllJourneys(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return journeyRepository.findAll(pageable);
    }

}

