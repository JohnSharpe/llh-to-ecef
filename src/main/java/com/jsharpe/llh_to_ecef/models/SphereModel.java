package com.jsharpe.llh_to_ecef.models;

import com.jsharpe.llh_to_ecef.dto.Coordinate;
import com.jsharpe.llh_to_ecef.dto.LonLatHeight;

/**
 * This model is a simple sphere, so there is only one radius.
 */
public class SphereModel extends Model {

    private final double radius;

    public SphereModel() {
        this.radius = DEFAULT_RADIUS;
    }

    public SphereModel(final double radius) {
        this.validateRadius(radius);
        this.radius = radius;
    }

    @Override
    public Coordinate translate(final LonLatHeight lonLatHeight) {
        this.validateLongitude(lonLatHeight.getLongitude());
        this.validateLatitude(lonLatHeight.getLatitude());

        final double longitude = lonLatHeight.getLongitude() * DEGREE_TO_RADIANS;
        final double latitude = lonLatHeight.getLatitude() * DEGREE_TO_RADIANS;
        final double height = lonLatHeight.getHeight();

        // These values are useful for x, y and z.
        // In a sphere, prime vertical radius (pvr) = radius!
        final double pvrPlusHeight = this.radius + height;

        final double cosLat = Math.cos(latitude);

        final double x = pvrPlusHeight * cosLat * Math.cos(longitude);
        final double y = pvrPlusHeight * cosLat * Math.sin(longitude);
        final double z = (this.radius + height) * Math.sin(latitude);

        return new Coordinate(x, y, z);
    }

}
