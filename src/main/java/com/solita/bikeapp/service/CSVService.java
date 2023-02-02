package com.solita.bikeapp.service;

import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.model.Bike;
import com.solita.bikeapp.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class CSVService {
    @Autowired
    BikeRepository repository;
    @Autowired
    CSVReader reader;

    public void saveAll() throws IOException {
        reader.readCSV();
        repository.saveAll(CSVReader.bikes);
    }

    public List<Bike> getAllBikes() {
        return repository.findAll();
    }
}

