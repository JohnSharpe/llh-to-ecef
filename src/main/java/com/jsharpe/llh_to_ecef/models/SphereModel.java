package com.jsharpe.llh_to_ecef.models;

import com.jsharpe.llh_to_ecef.dto.Coordinate;
import com.jsharpe.llh_to_ecef.dto.LonLatHeight;

/**
 * This model is a simple sphere, so radius is a single, constant value.
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

    // TODO Consider the name of this method - translate suggests movement, maybe transform?
    @Override
    public Coordinate translate(final LonLatHeight lonLatHeight) {
        this.validateLongitude(lonLatHeight.getLongitude());
        this.validateLatitude(lonLatHeight.getLatitude());

        final double cosLat = Math.cos(lonLatHeight.getLatitude());
        final double radPlusHeight = this.radius + lonLatHeight.getHeight();
        final double radPlusHeightByCosLat = radPlusHeight * cosLat;

        final double x = radPlusHeightByCosLat * Math.cos(lonLatHeight.getLongitude());
        final double y = radPlusHeightByCosLat * Math.sin(lonLatHeight.getLongitude());
        final double z = radPlusHeight * Math.sin(lonLatHeight.getLatitude());

        return new Coordinate(x, y, z);
    }

//    @Override
//    public LonLatHeight translate(Coordinate coordinate) {
//        // TODO
//        return null;
//    }

}
