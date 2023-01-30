package com.solita.bikeapp.service;

import com.solita.bikeapp.model.Bike;
import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
    @Autowired
    BikeRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Bike> bikes = CSVReader.csvToBike(file.getInputStream());
            repository.saveAll(bikes);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<Bike> getAllBikes() {
        return repository.findAll();
    }
}
