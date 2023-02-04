package com.solita.bikeapp.model;


import java.time.LocalDateTime;
import java.util.Objects;

public class Journey {

    private int journeyID;
    private LocalDateTime departureTime;
    private LocalDateTime returnTime;
    private int depStationID;
    private String depStationName;
    private int retStationID;
    private String retStationName;
    private float distance;
    private int duration;


    public Journey() {
    }

    public Journey(int journeyID, LocalDateTime departureTime, LocalDateTime returnTime, int depStationID, String depStationName, int retStationID, String retStationName, float distance, int duration) {
        this.journeyID = journeyID;
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.depStationID = depStationID;
        this.depStationName = depStationName;
        this.retStationID = retStationID;
        this.retStationName = retStationName;
        this.distance = distance;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Journey || o instanceof Integer)) return false;
        if (o instanceof Journey journey) {
            return getJourneyID() == journey.getJourneyID();
        } else {
            int journeyID = (int) o;
            return getJourneyID() == journeyID;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJourneyID());
    }

    @Override
    public String toString() {
        return "Journey{" +
                "journeyID=" + journeyID +
                ", departureTime=" + departureTime +
                ", returnTime=" + returnTime +
                ", depStationID=" + depStationID +
                ", depStationName='" + depStationName + '\'' +
                ", retStationID=" + retStationID +
                ", retStationName='" + retStationName + '\'' +
                ", distance=" + distance +
                ", duration=" + duration +
                '}';
    }

    public int getJourneyID() {
        return journeyID;
    }

    public void setJourneyID(int journeyID) {
        this.journeyID = journeyID;
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

    public Journey(LocalDateTime departureTime, LocalDateTime returnTime, int depStationID, String depStationName, int retStationID, String retStationName, float distance, int duration) {
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.depStationID = depStationID;
        this.depStationName = depStationName;
        this.retStationID = retStationID;
        this.retStationName = retStationName;
        this.distance = distance;
        this.duration = duration;
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