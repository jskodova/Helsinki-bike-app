package com.solita.bikeapp.controller;

import com.solita.bikeapp.entity.JourneyEntity;
import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.repository.JourneyRepository;
import com.solita.bikeapp.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    @GetMapping("/saveAll")
    public void save() throws IOException {
        reader.readCSV();
        service.saveAll();
    }

    @GetMapping("/journeys")
    public Page<JourneyEntity> getAllJourneys(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "10") int size) {
        return service.getAllJourneys(page, size);
    }

}
