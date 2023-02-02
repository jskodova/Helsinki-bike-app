package com.solita.bikeapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "bikes")
public class Bike {

    @Column(name = "departureTime")
    private LocalDateTime departureTime;
    @Column(name = "returnTime")
    private LocalDateTime returnTime;
    @Id
    @Column(name = "depStationID")
    private int depStationID;
    @Column(name = "depStationName")
    private String depStationName;
    @Column(name = "retStationID")
    private int retStationID;
    @Column(name = "retStationName")
    private String retStationName;
    @Column(name = "distance")
    private float distance;
    @Column(name = "duration")
    private int duration;


    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public int getDepStationID() {
        return depStationID;
    }

    public void setDepStationID(int depStationID) {
        this.depStationID = depStationID;
    }

    public String getDepStationName() {
        return depStationName;
    }

    public void setDepStationName(String depStationName) {
        this.depStationName = depStationName;
    }

    public int getRetStationID() {
        return retStationID;
    }

    public void setRetStationID(int retStationID) {
        this.retStationID = retStationID;
    }

    public String getRetStationName() {
        return retStationName;
    }

    public void setRetStationName(String retStationName) {
        this.retStationName = retStationName;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Bike(LocalDateTime departureTime, LocalDateTime returnTime, int depStationID, String depStationName, int retStationID, String retStationName, float distance, int duration) {
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.depStationID = depStationID;
        this.depStationName = depStationName;
        this.retStationID = retStationID;
        this.retStationName = retStationName;
        this.distance = distance;
        this.duration = duration;
    }

    public Bike() {
    }

    public Bike(LocalDateTime departureTime, LocalDateTime returnTime, String depStationID, String depStationName, String retStationID, String retStationName, float distance, int duration) {
    }

    @Override
    public String toString() {
        return "Bike{" +
                "departureTime=" + departureTime +
                ", returnTime=" + returnTime +
                ", depStationID=" + depStationID +
                ", depStationName='" + depStationName + '\'' +
                ", retStationID=" + retStationID +
                ", retStationName='" + retStationName + '\'' +
                ", distance=" + distance +
                ", duration=" + duration +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


}
