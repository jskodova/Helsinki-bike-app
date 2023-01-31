package com.solita.bikeapp.controller;

import com.solita.bikeapp.message.Response;
import com.solita.bikeapp.model.Bike;
import com.solita.bikeapp.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/csv")
public class BikeController {
    @Autowired
    CSVService fileService;

    @PostMapping("/upload")
    public ResponseEntity<Response> uploadFile() {
        String message;

        File file1 = null;
        File file2 = null;
        File file3 = null;
        try {
            file1 = new File("file1.csv");
            file2 = new File("file2.csv");
            file3 = new File("file3.csv");

            fileService.save((MultipartFile) file1);
            fileService.save((MultipartFile) file2);
            fileService.save((MultipartFile) file3);

            message = "Files upload was successful: " + file1.getName() + ", " + file2.getName() + ", " + file3.getName();
            return ResponseEntity.status(HttpStatus.OK).body(new Response(message));
        } catch (Exception e) {
            message = "Files upload was not successful: " + file1.getName() + ", " + file2.getName() + ", " + file3.getName() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Response(message));
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
