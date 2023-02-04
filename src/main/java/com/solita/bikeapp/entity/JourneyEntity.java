package com.solita.bikeapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "journeys")
public class JourneyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "journeyID")
    private int journeyID;
    @Column(name = "departureTime")
    private LocalDateTime departureTime;
    @Column(name = "returnTime")
    private LocalDateTime returnTime;
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

    public JourneyEntity(LocalDateTime departureTime, LocalDateTime returnTime, int depStationID, String depStationName, int retStationID, String retStationName, float distance, int duration) {
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
        if (!(o instanceof JourneyEntity that)) return false;
        return getJourneyID() == that.getJourneyID() && getDepStationID() == that.getDepStationID() && getRetStationID() == that.getRetStationID() && Float.compare(that.getDistance(), getDistance()) == 0 && getDuration() == that.getDuration() && Objects.equals(getDepartureTime(), that.getDepartureTime()) && Objects.equals(getReturnTime(), that.getReturnTime()) && Objects.equals(getDepStationName(), that.getDepStationName()) && Objects.equals(getRetStationName(), that.getRetStationName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJourneyID(), getDepartureTime(), getReturnTime(), getDepStationID(), getDepStationName(), getRetStationID(), getRetStationName(), getDistance(), getDuration());
    }

    @Column(name = "duration")
    private int duration;

    public JourneyEntity() {
    }

    public JourneyEntity(int journeyID, LocalDateTime departureTime, LocalDateTime returnTime, int depStationID, String depStationName, int retStationID, String retStationName, float distance, int duration) {
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
    public String toString() {
        return "JourneyEntity{" +
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