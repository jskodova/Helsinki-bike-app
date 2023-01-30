package com.solita.bikeapp.model;

import java.util.Date;

public record Bike() {
    static Date departureTime;
    static Date returnTime;
    static int depStationID;
    static String depStationName;
    static int retStationID;
    static String retStationName;
    static int distance;
    static int duration;

    public static Date getDepartureTime() {
        return departureTime;
    }

    public static void setDepartureTime(Date departureTime) {
        Bike.departureTime = departureTime;
    }

    public static Date getReturnTime() {
        return returnTime;
    }

    public static void setReturnTime(Date returnTime) {
        Bike.returnTime = returnTime;
    }

    public static int getDepStationID() {
        return depStationID;
    }

    public static void setDepStationID(int depStationID) {
        Bike.depStationID = depStationID;
    }

    public static String getDepStationName() {
        return depStationName;
    }

    public static void setDepStationName(String depStationName) {
        Bike.depStationName = depStationName;
    }

    public static int getRetStationID() {
        return retStationID;
    }

    public static void setRetStationID(int retStationID) {
        Bike.retStationID = retStationID;
    }

    public static String getRetStationName() {
        return retStationName;
    }

    public static void setRetStationName(String retStationName) {
        Bike.retStationName = retStationName;
    }

    public static int getDistance() {
        return distance;
    }

    public static void setDistance(int distance) {
        Bike.distance = distance;
    }

    public static int getDuration() {
        return duration;
    }

    public static void setDuration(int duration) {
        Bike.duration = duration;
    }

    public Bike {
    }
}
