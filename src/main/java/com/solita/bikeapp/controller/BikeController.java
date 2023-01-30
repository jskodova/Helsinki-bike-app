package com.solita.bikeapp.controller;

import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.model.Bike;
import com.solita.bikeapp.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/csv")
public class BikeController {
    @Autowired
    CSVService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVReader.hasCSVFormat(file)) {
            try {
                fileService.save(file);

                message = "File upload was successful: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "File upload was not successful: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please provide a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/bikes")
    public ResponseEntity<List<Bike>> getAllBikes() {
        try {
            List<Bike> bikes = fileService.getAllBikes();

            if (bikes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(bikes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
