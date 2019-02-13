package com.jsharpe.llh_to_ecef.dto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LonLatHeight that = (LonLatHeight) o;
        return Double.compare(that.longitude, longitude) == 0 &&
                Double.compare(that.latitude, latitude) == 0 &&
                Double.compare(that.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude, height);
    }

}
