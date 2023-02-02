package com.solita.bikeapp.controller;

import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.model.Bike;
import com.solita.bikeapp.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app")
public class BikeController {
    @Autowired
    CSVReader reader;
    @Autowired
    CSVService service;

    @GetMapping("/save")
    public void save() throws IOException {
        reader.readCSV();
        service.saveAll();
    }

    @GetMapping("/bikes")
    public ResponseEntity<?> getAllBikes(@RequestParam(defaultValue = "0") int pageNo,
                                         @RequestParam(defaultValue = "10") int pageSize,
                                         @RequestParam(defaultValue = "id") String sortBy) {
        List<Bike> bikes = service.getAllBikes();

        if (pageNo >= 0) {
            List<Bike> list = bikes.stream()
                    .skip((long) pageNo * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}