package com.solita.bikeapp.method;

import com.solita.bikeapp.model.Bike;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static String TYPE = "text/csv";
    static String[] headers = { "departureTime", "returnTime", "depStationID", "depStationName", "retStationID", "retStationName", "distance", "duration"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }
    public static List<Bike> csvToBike(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
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
