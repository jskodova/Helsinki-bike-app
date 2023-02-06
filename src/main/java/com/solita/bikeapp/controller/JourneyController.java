package com.solita.bikeapp.controller;

import com.solita.bikeapp.entity.JourneyEntity;
import com.solita.bikeapp.entity.StationEntity;
import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.repository.JourneyRepository;
import com.solita.bikeapp.repository.StationRepository;
import com.solita.bikeapp.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class JourneyController {
    @Autowired
    CSVReader reader;
    @Autowired
    AppService service;

    @Autowired
    JourneyRepository repository;

    @Autowired
    StationRepository stationRepository;

    //saves all journeys
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/saveAllJourneys")
    public void saveJourneys() throws IOException {
        reader.readCSV();
        service.saveAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/saveAllStations")
    public void saveStations() throws IOException {
        reader.readAddress();
        service.saveAllStations();
    }

    //displays all journeys
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/journeys")
    public List<JourneyEntity> getAllJourneys() {
        return service.getAllJourneys();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/stations")
    public List<StationEntity> getAllStations() {
        return service.getAllStations();
    }

    //displays a specific journey
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/journeys/{journeyID}")
    public Optional<JourneyEntity> getJourneyByID(@PathVariable long journeyID) {
        return repository.findById(journeyID);
    }

}
