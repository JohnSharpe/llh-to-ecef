package com.jsharpe.llh_to_ecef.models;

import com.jsharpe.llh_to_ecef.dto.Coordinate;
import com.jsharpe.llh_to_ecef.dto.LonLatHeight;

/**
 * This model is an ellipsoid, so it has 2 radii
 */
public class EllipsoidModel extends Model {

    private static final double DEGREE_TO_RADIANS = Math.PI / 180;

    // Equatorial radius
    private final double semiMajor;

    // Polar radius
    // private final double semiMinor;

    private final double semiMinorSquaredOverSemiMajorSquared;
    private final double eSquared;

    public EllipsoidModel() {
        this(DEFAULT_RADIUS, DEFAULT_RADIUS);
    }

    public EllipsoidModel(final double semiMajor, final double semiMinor) {
        this.validateRadius(semiMajor);
        this.validateRadius(semiMinor);

        this.semiMajor = semiMajor;
        //this.semiMinor = semiMinor;

        final double semiMajorSquared = Math.pow(this.semiMajor, 2);
        final double semiMinorSquared = Math.pow(semiMinor, 2);

        this.semiMinorSquaredOverSemiMajorSquared = semiMinorSquared / semiMajorSquared;
        this.eSquared = 1 - this.semiMinorSquaredOverSemiMajorSquared;
    }

    // TODO Consider the name of this method - translate suggests movement, maybe transform?
    public Coordinate translate(final LonLatHeight lonLatHeight) {
        this.validateLongitude(lonLatHeight.getLongitude());
        this.validateLatitude(lonLatHeight.getLatitude());

        final double longitude = lonLatHeight.getLongitude() * DEGREE_TO_RADIANS;
        final double latitude = lonLatHeight.getLatitude() * DEGREE_TO_RADIANS;
        final double height = lonLatHeight.getHeight();

        final double x, y, z;

        // This temp value is useful for x, y and z.
        final double primeVerticalRadius = calculatePrimeVerticalRadius(latitude);
        final double pvrPlusHeight = primeVerticalRadius + height;

        final double cosLat = Math.cos(latitude);

        x = pvrPlusHeight * cosLat * Math.cos(longitude);
        y = pvrPlusHeight * cosLat * Math.sin(longitude);
        z = ((this.semiMinorSquaredOverSemiMajorSquared * primeVerticalRadius) + height) * Math.sin(latitude);

        return new Coordinate(x, y, z);
    }

    private double calculatePrimeVerticalRadius(final double latitude) {
        return this.semiMajor /
                Math.sqrt(
                        1 - (
                                this.eSquared *
                                        Math.pow(
                                                Math.sin(latitude),
                                                2
                                        )
                        )
                );
    }

//    @Override
//    public LonLatHeight translate(Coordinate coordinate) {
//
//        final double x = coordinate.getX();
//        final double y = coordinate.getY();
//        final double z = coordinate.getZ();
//
//        final double longitude, latitude, height;
//
//
//        // TODO
//        return null;
//    }
}
