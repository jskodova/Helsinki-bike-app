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
    JourneyRepository journeyRepository;
    @Autowired
    CSVReader reader;

    //saves all entities in journeyrepository to the db
    public void saveAll() throws IOException {
        reader.readCSV();
        journeyRepository.saveAll(CSVReader.journeys);
    }

    //gets all journeys from the db
    public List<JourneyEntity> getAllJourneys() {
        return journeyRepository.findAll();
    }

}

