package com.solita.bikeapp.controller;

import com.solita.bikeapp.message.Response;
import com.solita.bikeapp.method.CSVReader;
import com.solita.bikeapp.model.Bike;
import com.solita.bikeapp.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/api/csv")
public class BikeController {
    @Autowired
    CSVService fileService;

    @PostMapping("/savetoDB")
    public ResponseEntity<Response> savetoDB(@RequestParam("file") File file) {
        String message;
        if (CSVReader.isCSV(file)) {
            try {
                fileService.saveAllFilesInRepository();

                message = "Files reading was successful!";
                return ResponseEntity.status(HttpStatus.OK).body(new Response(message));
            } catch (Exception e) {
                message = "Files reading was not successful!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Response(message));
            }
        }
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
