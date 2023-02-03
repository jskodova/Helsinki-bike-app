package com.solita.bikeapp.controller;

import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.model.Journey;
import com.solita.bikeapp.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin("localhost:3000")
@RestController
@RequestMapping("/app")
public class JourneyController {
    @Autowired
    CSVReader reader;
    @Autowired
    AppService service;

    @GetMapping("/save")
    public void save() throws IOException {
        reader.readCSV();
        service.saveAll();
    }

    @PostMapping("/journey")
    public Journey createJourney(@RequestBody Journey journey) {
        return AppService.createJourney(journey);
    }
}