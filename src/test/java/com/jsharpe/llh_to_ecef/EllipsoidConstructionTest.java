package com.jsharpe.llh_to_ecef;

import com.jsharpe.llh_to_ecef.models.EllipsoidModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EllipsoidConstructionTest {

    @Test
    void testBasicContruction() {
        // Given

        // When
        new EllipsoidModel();

        // Then
    }

    @Test
    void testParameterisedConstruction() {
        // Given
        final double semiMajor = 6378;
        final double semiMinor = 6356;

        // When
        new EllipsoidModel(semiMajor, semiMinor);

        // Then
    }

    @Test
    void testZeroRadiusConstruction() {
        // Given
        final double radius = 0;

        // When
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new EllipsoidModel(radius, radius)
        );

        // Then (exception)
    }

    @Test
    void testNegativeRadiusConstruction() {
        // Given
        final double radius = -1232.14214;

        // When
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new EllipsoidModel(radius, radius)
        );

        // Then (exception)
    }

}
