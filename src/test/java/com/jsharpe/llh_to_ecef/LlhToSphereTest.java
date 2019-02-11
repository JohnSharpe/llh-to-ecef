package com.jsharpe.llh_to_ecef;

import com.jsharpe.llh_to_ecef.dto.Coordinate;
import com.jsharpe.llh_to_ecef.dto.LonLatHeight;
import com.jsharpe.llh_to_ecef.models.Model;
import com.jsharpe.llh_to_ecef.models.SphereModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LlhToSphereTest {

    @Test
    void testZeroesIn() {
        // Given
        final double radius = 1;
        final Model sphereModel = new SphereModel(1);
        final LonLatHeight lonLatHeight = new LonLatHeight(0, 0, 0);

        // When TODO Consider the name of this method - translate suggests movement, maybe transform?
        final Coordinate coordinate = sphereModel.translate(lonLatHeight);

        // Then
        Assertions.assertEquals(radius, coordinate.getX());
        Assertions.assertEquals(0, coordinate.getY());
        Assertions.assertEquals(0, coordinate.getZ());
    }

    @Test
    void testUnderMinimumLongitude() {
        // Given
        final Model sphereModel = new SphereModel(1);
        final LonLatHeight lonLatHeight = new LonLatHeight(-180.001, 0, 0);

        // When
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sphereModel.translate(lonLatHeight)
        );
    }

    @Test
    void testOverMaximumLongitude() {
        // Given
        final Model sphereModel = new SphereModel(1);
        final LonLatHeight lonLatHeight = new LonLatHeight(180.001, 0, 0);

        // When
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sphereModel.translate(lonLatHeight)
        );
    }

    @Test
    void testUnderMinimumLatitude() {
        // Given
        final Model sphereModel = new SphereModel(1);
        final LonLatHeight lonLatHeight = new LonLatHeight(0, -90.001, 0);

        // When
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sphereModel.translate(lonLatHeight)
        );
    }

    @Test
    void testOverMaximumLatitude() {
        // Given
        final Model sphereModel = new SphereModel(1);
        final LonLatHeight lonLatHeight = new LonLatHeight(0, 90.001, 0);

        // When
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sphereModel.translate(lonLatHeight)
        );
    }

    // TODO Use a different, paramterized test with a load of pre-calculated values for correct translations.

}
