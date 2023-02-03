package com.solita.bikeapp.model;


import java.time.LocalDateTime;
import java.util.Objects;

public class Journey {

    private LocalDateTime departureTime;
    private LocalDateTime returnTime;
    private int depStationID;
    private String depStationName;
    private int retStationID;
    private String retStationName;
    private float distance;
    private int duration;


    @Override
    public String toString() {
        return "Journey{" +
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return depStationID == journey.depStationID && retStationID == journey.retStationID && Float.compare(journey.distance, distance) == 0 && duration == journey.duration && Objects.equals(departureTime, journey.departureTime) && Objects.equals(returnTime, journey.returnTime) && Objects.equals(depStationName, journey.depStationName) && Objects.equals(retStationName, journey.retStationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureTime, returnTime, depStationID, depStationName, retStationID, retStationName, distance, duration);
    }

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
}