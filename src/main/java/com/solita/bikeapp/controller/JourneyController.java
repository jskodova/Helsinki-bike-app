package com.solita.bikeapp.controller;

import com.solita.bikeapp.entity.JourneyEntity;
import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.repository.JourneyRepository;
import com.solita.bikeapp.repository.StationRepository;
import com.solita.bikeapp.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
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
    @GetMapping("/saveAll")
    public void save() throws IOException {
        reader.readCSV();
        service.saveAll();
    }

    //displays all journeys
    @GetMapping("/journeys")
    public Page<JourneyEntity> getAllJourneys(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "10") int size) {
        return service.getAllJourneys(page, size);
    }

    //displays a specific journey
    @GetMapping("/journeys/{journeyID}")
    public Optional<JourneyEntity> getJourneyByID(@PathVariable long journeyID) {
        return repository.findById(journeyID);
    }

    //endpoint for seeing unique stations and their count of occurences
    @GetMapping("/stations")
    public List<String[]> getUniqueStationNames() {
        List<String[]> depStationNames = stationRepository.countUniqueDepStationName();
        List<String[]> retStationNames = stationRepository.countUniqueRetStationName();
        List<String[]> allStationNames = new ArrayList<>();
        allStationNames.addAll(depStationNames);
        allStationNames.addAll(retStationNames);
        return allStationNames;
    }
}
