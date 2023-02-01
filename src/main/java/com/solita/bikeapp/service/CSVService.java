package com.solita.bikeapp.service;

import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.model.Bike;
import com.solita.bikeapp.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class CSVService {
    @Autowired
    BikeRepository repository;

    public void save(File file) {
        List<Bike> bikes = CSVReader.csvToBikes(file);
        repository.saveAll(bikes);
    }

    public List<Bike> getAllBikes() {
        return repository.findAll();
    }

    public void saveAllFilesInRepository() {
        File[] files = new File("resources/csv").listFiles();
        for (File file : files) {
            save(file);
        }
    }
}
