package com.jsharpe.llh_to_ecef;

import com.jsharpe.llh_to_ecef.models.SphereModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SphereConstructionTest {

    @Test
    void testBasicContruction() {
        // Given

        // When
        new SphereModel();

        // Then
    }

    @Test
    void testParameterisedConstruction() {
        // Given
        final double radius = 6371;

        // When
        new SphereModel(radius);

        // Then
    }

    @Test
    void testZeroRadiusConstruction() {
        // Given
        final double radius = 0;

        // When
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new SphereModel(radius)
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
                () -> new SphereModel(radius)
        );

        // Then (exception)
    }

}
