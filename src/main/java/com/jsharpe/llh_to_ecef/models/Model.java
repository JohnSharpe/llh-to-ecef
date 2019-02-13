package com.jsharpe.llh_to_ecef.models;

import com.jsharpe.llh_to_ecef.dto.Coordinate;
import com.jsharpe.llh_to_ecef.dto.LonLatHeight;

public abstract class Model {

    protected static final double DEGREE_TO_RADIANS = Math.PI / 180;

    private static final String BAD_RADIUS_MESSAGE = "Radius of a sphere may not be 0 or negative.";

    private static final double LATITUDE_MINIMUM = -90;
    private static final String LATITUDE_UNDER_MINIMUM_MESSAGE =
            String.format("Latitude may not be less than %f", LATITUDE_MINIMUM);

    private static final double LATITUDE_MAXIMUM = 90;
    private static final String LATITUDE_OVER_MAXIMUM_MESSAGE =
            String.format("Latitude may not be greater than %f", LATITUDE_MAXIMUM);

    private static final double LONGITUDE_MINIMUM = -180;
    private static final String LONGITUDE_UNDER_MINIMUM_MESSAGE =
            String.format("Longitude may not be less than %f", LONGITUDE_MINIMUM);

    private static final double LONGITUDE_MAXIMUM = 180;
    private static final String LONGITUDE_OVER_MAXIMUM_MESSAGE =
            String.format("Longitude may not be greater than %f", LONGITUDE_MAXIMUM);

    static final double DEFAULT_RADIUS = 1;

    void validateRadius(final double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException(BAD_RADIUS_MESSAGE);
        }
    }

    void validateLatitude(final double latitude) {
        if (latitude < LATITUDE_MINIMUM) {
            throw new IllegalArgumentException(LATITUDE_UNDER_MINIMUM_MESSAGE);
        }
        if (latitude > LATITUDE_MAXIMUM) {
            throw new IllegalArgumentException(LATITUDE_OVER_MAXIMUM_MESSAGE);
        }
    }

    void validateLongitude(final double longitude) {
        if (longitude < LONGITUDE_MINIMUM) {
            throw new IllegalArgumentException(LONGITUDE_UNDER_MINIMUM_MESSAGE);
        }
        if (longitude > LONGITUDE_MAXIMUM) {
            throw new IllegalArgumentException(LONGITUDE_OVER_MAXIMUM_MESSAGE);
        }
    }

    public abstract Coordinate translate(final LonLatHeight lonLatHeight);

    public static Model earthKilometers() {
        return new EllipsoidModel(6378.1370, 6356.752314245);
    }

    public static Model earthMiles() {
        return new EllipsoidModel(3963.1906, 3949.9028);
    }

}
