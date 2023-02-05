package com.solita.bikeapp.method;

import com.solita.bikeapp.entity.JourneyEntity;
import com.solita.bikeapp.repository.JourneyRepository;
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

//Service for reading the csv files and adding them to db
@Service
public class CSVReader {

    //List of csv files
    List<String> fileNames;

    //setting headers for the csv files so they can be ignored, also avoids the byte reading error with CSV Parser
    static String[] HEADERS = {"Departure", "Return", "Departure station id", "Departure station name", "Return station id", "Return station name", "Covered distance (m)", "Duration (sec.)"};

    public static List<JourneyEntity> journeys = new ArrayList<>();
    @Autowired
    public final JourneyRepository journeyRepository;

    //this constructor with added csv files, only one is added because of size restrictions
    public CSVReader(List<String> fileNames, JourneyRepository journeyRepository) {
        this.fileNames = fileNames;
        this.journeyRepository = journeyRepository;
        fileNames.add("src/main/resources/csv/2021-05.csv");
        //these csv were too big to put on github
        //fileNames.add("src/main/resources/csv/2021-06.csv");
        //fileNames.add("src/main/resources/csv/2021-07.csv");
    }

    //reads the csv using BufferedReader and parses it to a JourneyEntity
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
            //looking for errors in the csv file
            try {
                for (final CSVRecord csvRecord : csvParser) {
                    String csvError = validateCsvRecord(csvRecord);
                    if (csvError != null && !csvError.isEmpty()) {
                        System.out.println("Error in CSV File: " + csvError);
                        return;
                    }
                    JourneyEntity journey = new JourneyEntity();
                    journey.setDepartureTime(LocalDateTime.parse(csvRecord.get("Departure")));
                    journey.setReturnTime(LocalDateTime.parse(csvRecord.get("Return")));
                    journey.setDepStationID(Integer.parseInt(csvRecord.get("Departure station id")));
                    journey.setDepStationName(csvRecord.get("Departure station name"));
                    journey.setRetStationID(Integer.parseInt(csvRecord.get("Return station id")));
                    journey.setRetStationName(csvRecord.get("Return station name"));
                    journey.setDistance((Float.parseFloat(csvRecord.get("Covered distance (m)"))) / 10);
                    journey.setDuration(Integer.parseInt(csvRecord.get("Duration (sec.)")));

                    if (journey.getDistance() > 0.01 && journey.getDuration() > 10) {
                        journeys.add(journey);
                        journeyRepository.save(journey);
                    }
                    journeys.add(journey);
                    journeyRepository.save(journey);
                }
            } finally {
                fileReader.close();
            }
        }
    }

    //simple csv validation
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