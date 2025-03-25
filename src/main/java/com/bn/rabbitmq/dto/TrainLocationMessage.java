package com.bn.rabbitmq.dto;

import java.io.Serializable;

public class TrainLocationMessage implements Serializable {
    private String trainCode;
    private Integer stationIdStart;
    private Integer stationIdEnd;
    private double averageSpeed;
    private double distance;
    private Integer x;
    private Integer y;

    // Constructor
    public TrainLocationMessage() {
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public Integer getStationIdStart() {
        return stationIdStart;
    }

    public void setStationIdStart(Integer stationIdStart) {
        this.stationIdStart = stationIdStart;
    }

    public Integer getStationIdEnd() {
        return stationIdEnd;
    }

    public void setStationIdEnd(Integer stationIdEnd) {
        this.stationIdEnd = stationIdEnd;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public TrainLocationMessage(String trainCode, Integer stationIdStart, Integer stationIdEnd, double averageSpeed, double distance, Integer x, Integer y) {
        this.trainCode = trainCode;
        this.stationIdStart = stationIdStart;
        this.stationIdEnd = stationIdEnd;
        this.averageSpeed = averageSpeed;
        this.distance = distance;
        this.x = x;
        this.y = y;
    }
}