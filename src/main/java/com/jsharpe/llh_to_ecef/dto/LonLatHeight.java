package com.jsharpe.llh_to_ecef.dto;

public class LonLatHeight {

    private final double longitude;
    private final double latitude;
    private final double height;

    public LonLatHeight(double longitude, double latitude, double height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getHeight() {
        return height;
    }

}
