package com.solita.bikeapp.method;

import com.solita.bikeapp.entity.JourneyEntity;
import com.solita.bikeapp.entity.StationEntity;
import com.solita.bikeapp.repository.JourneyRepository;
import com.solita.bikeapp.repository.StationRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//Service for reading the csv files and adding them to db
@Service
public class CSVReader {

    //List of csv files
    List<String> fileNames;
    List<String> addresses;

    //setting headers for the csv files so they can be ignored, also avoids the byte reading error with CSV Parser
    static String[] HEADERS = {"Departure", "Return", "Departure station id", "Departure station name", "Return station id", "Return station name", "Covered distance (m)", "Duration (sec.)"};
    static String[] HEADERS2 = {"FID", "ID", "Nimi", "Namn", "Name", "Osoite", "Adress", "Kaupunki", "Stad", "Operaattor", "Kaapasiteet", "x", "y"};

    public static List<JourneyEntity> journeys = new ArrayList<>();
    public static List<StationEntity> stations = new ArrayList<>();
    @Autowired
    public final JourneyRepository journeyRepository;

    @Autowired
    public final StationRepository stationRepository;


    //this constructor with added csv files, only one is added because of size restrictions
    public CSVReader(List<String> fileNames, List<String> addresses, JourneyRepository journeyRepository, StationRepository stationRepository) {
        this.addresses = addresses;
        this.fileNames = fileNames;
        this.journeyRepository = journeyRepository;
        this.stationRepository = stationRepository;
        fileNames.add("src/main/resources/csv/2021-05.csv");
        addresses.add("src/main/resources/csv/addresses.csv");
        //these csv were too big to put on github
        //fileNames.add("src/main/resources/csv/2021-06.csv");
        //fileNames.add("src/main/resources/csv/2021-07.csv");
    }

    //reads the csv using BufferedReader and parses it to a JourneyEntity
    public void readCSV() throws IOException {
        for (String fileName : fileNames) {
            final BufferedReader fileReader = new BufferedReader((new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8)));
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
                    journey.setDistance((Float.parseFloat(csvRecord.get("Covered distance (m)"))) / 1000);
                    journey.setDuration(Integer.parseInt(csvRecord.get("Duration (sec.)")));

                    if (journey.getDistance() > 0.01 && journey.getDuration() > 10) {
                        journeys.add(journey);
                        journeyRepository.save(journey);
                    }
                }
            } finally {
                fileReader.close();
                csvParser.close();
            }
        }
    }

    public void readAddress() throws IOException {
        for (String address : addresses) {
            final BufferedReader addressReader = new BufferedReader((new InputStreamReader(new FileInputStream(address), StandardCharsets.UTF_8)));
            System.out.print("Trying to read csv");

            CSVParser addressParser = new CSVParser(addressReader,
                    CSVFormat.Builder.create()
                            .setHeader(HEADERS2)
                            .setIgnoreEmptyLines(true)
                            .setSkipHeaderRecord(true)
                            .setTrim(true)
                            .build());
            try {
                for (final CSVRecord addressRecord : addressParser) {
                    StationEntity station = new StationEntity();
                    station.setStationName(addressRecord.get("Nimi"));
                    station.setStationID(Integer.parseInt(addressRecord.get("ID")));
                    station.setAddress(addressRecord.get("Osoite"));
                    station.setCity(addressRecord.get("Kaupunki"));
                    station.setCoorX(Float.parseFloat(addressRecord.get(("x"))));
                    station.setCoorY(Float.parseFloat(addressRecord.get(("y"))));

                    int occurrenceDep = 0;
                    int occurrenceRet = 0;
                    List<JourneyEntity> journeys = journeyRepository.findAll();
                    for (JourneyEntity journey : journeys) {
                        if (station.getStationName().equals(journey.getDepStationName())) {
                            occurrenceDep++;
                        }
                        if (station.getStationName().equals(journey.getRetStationName())) {
                            occurrenceRet++;
                        }
                    }
                    station.setCountDep(occurrenceDep);
                    station.setCountRet(occurrenceRet);
                    stations.add(station);
                    stationRepository.save(station);
                }
            } finally {
                addressReader.close();
                addressParser.close();
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