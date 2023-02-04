package com.solita.bikeapp.service;

import com.solita.bikeapp.entity.JourneyEntity;
import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AppService {
    @Autowired
    JourneyRepository repository;
    @Autowired
    CSVReader reader;

    public void saveAll() throws IOException {
        reader.readCSV();
        repository.saveAll(CSVReader.journeys);
    }

    public List<JourneyEntity> getAllJourneys() {
        return repository.findAll();
    }

}

