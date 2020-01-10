package com.jsharpe.llh_to_ecef;

import com.jsharpe.llh_to_ecef.dto.Coordinate;
import com.jsharpe.llh_to_ecef.dto.LonLatHeight;
import com.jsharpe.llh_to_ecef.models.Model;
import com.jsharpe.llh_to_ecef.models.SphereModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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

    @ParameterizedTest
    @MethodSource("provider")
    void testSphere(final LonLatHeight lonLatHeight, final Coordinate coordinate) {
        // Given
        final Model sphere = new SphereModel(100);

        // When
        final Coordinate result = sphere.translate(lonLatHeight);

        // Then (test values are only to 3 dp accuracy)
        Assertions.assertEquals(coordinate.getX(), result.getX(), 0.001);
        Assertions.assertEquals(coordinate.getY(), result.getY(), 0.001);
        Assertions.assertEquals(coordinate.getZ(), result.getZ(), 0.001);
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                // Origin
                Arguments.of(
                        new LonLatHeight(-13, 42, -100),
                        new Coordinate(0, 0, 0)
                ),
                // Front
                Arguments.of(
                        new LonLatHeight(0, 0, 0),
                        new Coordinate(100, 0, 0)
                ),
                // Side
                Arguments.of(
                        new LonLatHeight(90, 0, 0),
                        new Coordinate(0, 100, 0)
                ),
                // Other Side
                Arguments.of(
                        new LonLatHeight(-90, 0, 0),
                        new Coordinate(0, -100, 0)
                ),
                // Rear
                Arguments.of(
                        new LonLatHeight(180, 0, 0),
                        new Coordinate(-100, 0, 0)
                ),
                // Bottom
                Arguments.of(
                        new LonLatHeight(0, 90, 0),
                        new Coordinate(0, 0, 100)
                )

                // TODO Could always do more
                // TODO But really. Apart from the extremes, work out some numbers on paper and make sure we match
        );
    }

}
