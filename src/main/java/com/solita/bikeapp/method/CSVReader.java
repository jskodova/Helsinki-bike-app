package com.solita.bikeapp.method;

import com.solita.bikeapp.model.Bike;
import com.solita.bikeapp.repository.BikeRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVReader {

    List<String> fileNames;
    static String[] HEADERS = {"Departure", "Return", "Departure station id", "Departure station name", "Return station id", "Return station name", "Covered distance (m)", "Duration (sec.)"};
    public static List<Bike> bikes = new ArrayList<>();
    @Autowired
    public final BikeRepository bikeRepository;

    public CSVReader(List<String> fileNames, BikeRepository bikeRepository) {
        this.fileNames = fileNames;
        this.bikeRepository = bikeRepository;
        fileNames.add("src/main/resources/csv/2021-05.csv");
    }

    public void readCSV() throws IOException {
        for (String fileName : fileNames) {
            final BufferedReader fileReader = new BufferedReader(new FileReader((fileName)));
            System.out.print("Trying to read csv");

            CSVParser csvParser = new CSVParser(fileReader,
                    CSVFormat.Builder.create()
                            .setHeader(HEADERS)
                            .setIgnoreEmptyLines(true)
                            .setSkipHeaderRecord(true)
                            .setTrim(true)
                            .build());
            try {
                for (final CSVRecord csvRecord : csvParser) {
                    String csvError = validateCsvRecord(csvRecord);
                    if (csvError != null && !csvError.isEmpty()) {
                        System.out.println("Error in CSV File: " + csvError);
                        return;
                    }
                    Bike bike = new Bike();
                    bike.setDepartureTime(LocalDateTime.parse(csvRecord.get("Departure")));
                    bike.setReturnTime(LocalDateTime.parse(csvRecord.get("Return")));
                    bike.setDepStationID(Integer.parseInt(csvRecord.get("Departure station id")));
                    bike.setDepStationName(csvRecord.get("Departure station name"));
                    bike.setRetStationID(Integer.parseInt(csvRecord.get("Return station id")));
                    bike.setRetStationName(csvRecord.get("Return station name"));
                    bike.setDistance((Float.parseFloat(csvRecord.get("Covered distance (m)"))) / 10);
                    bike.setDuration(Integer.parseInt(csvRecord.get("Duration (sec.)")));

                    if (bike.getDistance() > 0.01 && bike.getDuration() > 10) {
                        bikes.add(bike);
                        bikeRepository.save(bike);
                    }
                    bikes.add(bike);
                    bikeRepository.save(bike);
                }
            } finally {
                fileReader.close();
            }
        }
    }

    private String validateCsvRecord(CSVRecord csvRecord) {
        if (csvRecord.get("Departure") == null || csvRecord.get("Departure").isEmpty()) {
            return "Departure field is empty";
        }
        if (csvRecord.get("Return") == null || csvRecord.get("Return").isEmpty()) {
            return "Return field is empty";
        }
        if (csvRecord.get("Departure station id") == null || csvRecord.get("Departure station id").isEmpty()) {
            return "Departure station id field is empty";
        }
        if (csvRecord.get("Departure station name") == null || csvRecord.get("Departure station name").isEmpty()) {
            return "Departure station name field is empty";
        }
        if (csvRecord.get("Return station id") == null || csvRecord.get("Return station id").isEmpty()) {
            return "Return station id field is empty";
        }
        if (csvRecord.get("Return station name") == null || csvRecord.get("Return station name").isEmpty()) {
            return "Return station name field is empty";
        }
        if (csvRecord.get("Covered distance (m)") == null || csvRecord.get("Covered distance (m)").isEmpty()) {
            return "Covered distance (m) field is empty";
        }
        if (csvRecord.get("Duration (sec.)") == null || csvRecord.get("Duration (sec.)").isEmpty()) {
            return "Duration (sec.) field is empty";
        }
        return null;
    }
}