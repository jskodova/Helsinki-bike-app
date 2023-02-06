package com.solita.bikeapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StationEntity {
    @Id
    public int stationID;
    public String stationName;
    public String address;
    public String city;
    public float coorX;
    public float coorY;

    public int countRet;
    public int countDep;

    public double avgRet;
    public double avgDep;


    public double getAvgRet() {
        return avgRet;
    }

    public void setAvgRet(double avgRet) {
        this.avgRet = avgRet;
    }

    public double getAvgDep() {
        return avgDep;
    }

    public StationEntity(String stationName, String address, String city) {
        this.stationName = stationName;
        this.address = address;
        this.city = city;
    }

    public void setAvgDep(double avgDep) {
        this.avgDep = avgDep;
    }

    public StationEntity() {
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getCoorX() {
        return coorX;
    }

    public void setCoorX(float coorX) {
        this.coorX = coorX;
    }

    public float getCoorY() {
        return coorY;
    }

    public void setCoorY(float coorY) {
        this.coorY = coorY;
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

    public StationEntity(int stationID, String stationName, String address, String city, float coorX, float coorY, int countRet, int countDep, double avgRet, double avgDep) {
        this.stationID = stationID;
        this.stationName = stationName;
        this.address = address;
        this.city = city;
        this.coorX = coorX;
        this.coorY = coorY;
        this.countRet = countRet;
        this.countDep = countDep;
        this.avgRet = avgRet;
        this.avgDep = avgDep;
    }
}
