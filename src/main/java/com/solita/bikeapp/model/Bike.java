package com.solita.bikeapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.apache.logging.log4j.message.AsynchronouslyFormattable;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;



@Entity
@Table(name = "bike")
public class Bike {
    @Id
    @Column(name = "departureTime")
    private Date departureTime;
    @Id
    @Column(name = "returnTime")
    private Date returnTime;
    @Id
    @Column(name = "depStationID")
    private int depStationID;
    @Id
    @Column(name = "depStationName")
    private String depStationName;
    @Id
    @Column(name = "retStationID")
    private int retStationID;
    @Id
    @Column(name = "retStationName")
    private String retStationName;
    @Id
    @Column(name = "distance")
    private int distance;
    @Id
    @Column(name = "duration")
    private int duration;

    public Bike() {
    }
}
