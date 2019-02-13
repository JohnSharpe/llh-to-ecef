package com.jsharpe.llh_to_ecef.models;

import com.jsharpe.llh_to_ecef.dto.Coordinate;
import com.jsharpe.llh_to_ecef.dto.LonLatHeight;

/**
 * This model is an ellipsoid, so it has 2 radii: a semi-major (equatorial) and a semi-minor (polar)
 */
public class EllipsoidModel extends Model {

    // Equatorial radius
    private final double semiMajor;

    // Polar radius (semi-minor) is not useful to us.

    private final double semiMinorSquaredOverSemiMajorSquared;
    private final double eSquared;

    public EllipsoidModel() {
        this(DEFAULT_RADIUS, DEFAULT_RADIUS);
    }

    // Do as much work here as possible
    public EllipsoidModel(final double semiMajor, final double semiMinor) {
        this.validateRadius(semiMajor);
        this.validateRadius(semiMinor);

        this.semiMajor = semiMajor;

        final double semiMajorSquared = Math.pow(this.semiMajor, 2);
        final double semiMinorSquared = Math.pow(semiMinor, 2);

        this.semiMinorSquaredOverSemiMajorSquared = semiMinorSquared / semiMajorSquared;
        this.eSquared = 1 - this.semiMinorSquaredOverSemiMajorSquared;
    }

    public Coordinate translate(final LonLatHeight lonLatHeight) {
        this.validateLongitude(lonLatHeight.getLongitude());
        this.validateLatitude(lonLatHeight.getLatitude());

        final double longitude = lonLatHeight.getLongitude() * DEGREE_TO_RADIANS;
        final double latitude = lonLatHeight.getLatitude() * DEGREE_TO_RADIANS;
        final double height = lonLatHeight.getHeight();

        // These values are useful for x, y and z.
        final double sinLat = Math.sin(latitude);
        final double primeVerticalRadius = calculatePrimeVerticalRadius(sinLat);
        final double pvrPlusHeight = primeVerticalRadius + height;

        final double cosLat = Math.cos(latitude);

        final double x = pvrPlusHeight * cosLat * Math.cos(longitude);
        final double y = pvrPlusHeight * cosLat * Math.sin(longitude);
        final double z = ((this.semiMinorSquaredOverSemiMajorSquared * primeVerticalRadius) + height) * sinLat;

        return new Coordinate(x, y, z);
    }

    private double calculatePrimeVerticalRadius(final double sinLat) {
        return this.semiMajor /
                Math.sqrt(
                        1 - (
                                this.eSquared *
                                        Math.pow(sinLat, 2)
                        )
                );
    }

}
