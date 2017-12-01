package com.example.shipmenttracking.model;

/**
 * Created by anura on 11/21/2017.
 */

public class Shipment {

    private String trackingNumber;
    private long timestamp;
    private long shipmentCreateDate;

    public Shipment() {

        //Zero Argument constructor needed for retrofit
    }

    public Shipment(String trackingNumber, long timestamp, long shipmentCreateDate) {
        this.trackingNumber = trackingNumber;
        this.timestamp = timestamp;
        this.shipmentCreateDate = shipmentCreateDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getShipmentCreateDate() {
        return shipmentCreateDate;
    }

    public void setShipmentCreateDate(long shipmentCreateDate) {
        this.shipmentCreateDate = shipmentCreateDate;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "trackingNumber='" + trackingNumber + '\'' +
                ", timestamp=" + timestamp +
                ", shipmentCreateDate=" + shipmentCreateDate +
                '}';
    }
}
