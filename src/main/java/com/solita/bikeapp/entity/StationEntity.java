package com.solita.bikeapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StationEntity {
    @Id
    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getCountRet() {
        return countRet;
    }

    public void setCountRet(int countRet) {
        this.countRet = countRet;
    }

    public int getCountDep() {
        return countDep;
    }

    public void setCountDep(int countDep) {
        this.countDep = countDep;
    }

    public int stationID;
    public String stationName;
    public int countRet;
    public int countDep;
}
