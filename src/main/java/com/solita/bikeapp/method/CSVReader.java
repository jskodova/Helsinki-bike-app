package com.solita.bikeapp.method;

import com.solita.bikeapp.model.Bike;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static String TYPE = "text/csv";
    static String[] headers = {"departureTime", "returnTime", "depStationID", "depStationName", "retStationID", "retStationName", "distance", "duration"};

    public static boolean isCSV(File csvfile) {
        try {
            FileReader reader = new FileReader(csvfile);
            Scanner scanner = new Scanner(reader);
            String line = scanner.nextLine();
            String[] values = line.split(",");
            if (values.length > 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Bike> csvToBikes(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file));
             CSVParser csvParser = new CSVParser(br,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Bike> bikes = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Bike bike = new Bike(
                        Date.valueOf(csvRecord.get("departureTime")),
                        Date.valueOf(csvRecord.get("returnTime")),
                        csvRecord.get("depStationID"),
                        csvRecord.get("depStationName"),
                        csvRecord.get("retStationID"),
                        csvRecord.get("retStationName"),
                        Integer.parseInt(csvRecord.get("distance")),
                        Integer.parseInt(csvRecord.get("duration"))
                );
                bikes.add(bike);
            }

            return bikes;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}