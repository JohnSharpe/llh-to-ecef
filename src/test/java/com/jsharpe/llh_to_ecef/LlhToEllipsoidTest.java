package com.jsharpe.llh_to_ecef;

import com.jsharpe.llh_to_ecef.dto.Coordinate;
import com.jsharpe.llh_to_ecef.dto.LonLatHeight;
import com.jsharpe.llh_to_ecef.models.EllipsoidModel;
import com.jsharpe.llh_to_ecef.models.Model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

class LlhToEllipsoidTest {

    @Test
    void testZeroesIn() {
        // Given
        final double semiMajor = 2;
        final double semiMinor = 1;
        final Model sphereModel = new EllipsoidModel(semiMajor, semiMinor);
        final LonLatHeight lonLatHeight = new LonLatHeight(0, 0, 0);

        // When TODO Consider the name of this method - translate suggests movement, maybe transform?
        final Coordinate coordinate = sphereModel.translate(lonLatHeight);

        // Then
        Assertions.assertEquals(semiMajor, coordinate.getX());
        Assertions.assertEquals(0, coordinate.getY());
        Assertions.assertEquals(0, coordinate.getZ());
    }

    @Test
    void testUnderMinimumLongitude() {
        // Given
        final Model sphereModel = new EllipsoidModel(2, 1);
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
        final Model sphereModel = new EllipsoidModel(2, 1);
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
        final Model sphereModel = new EllipsoidModel(2, 1);
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
        final Model sphereModel = new EllipsoidModel(2, 1);
        final LonLatHeight lonLatHeight = new LonLatHeight(0, 90.001, 0);

        // When
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sphereModel.translate(lonLatHeight)
        );
    }

    @ParameterizedTest
    @MethodSource("kilometerProvider")
    void testNormalKilometerValuesToXYZ(final LonLatHeight lonLatHeight, final Coordinate coordinate) {
        // Given
        final Model earth = Model.earthKilometers();

        // When
        final Coordinate result = earth.translate(lonLatHeight);

        // Then (test values are only to 3 dp accuracy)
        Assertions.assertEquals(coordinate.getX(), result.getX(), 0.001);
        Assertions.assertEquals(coordinate.getY(), result.getY(), 0.001);
        Assertions.assertEquals(coordinate.getZ(), result.getZ(), 0.001);
    }

    @ParameterizedTest
    @MethodSource("kilometerProvider")
    void testNormalKilometerValuesToLLH(final LonLatHeight lonLatHeight, final Coordinate coordinate) {
        // Given
        final Model earth = Model.earthKilometers();

        // When
        final LonLatHeight result = earth.translate(coordinate);

        // Then
        Assertions.assertEquals(lonLatHeight.getLongitude(), result.getLongitude());
        Assertions.assertEquals(lonLatHeight.getLatitude(), result.getLatitude());
        Assertions.assertEquals(lonLatHeight.getHeight(), result.getHeight());
        Assertions.assertEquals(0, result.getHeight());
    }

    // Values taken from http://www.oc.nps.edu/oc2902w/coord/llhxyz.htm
    private static Stream<Arguments> kilometerProvider() {
        return Stream.of(
                // London
                Arguments.of(
                        new LonLatHeight(-0.1277583, 51.5073509, 0),
                        new Coordinate(3977.999, -8.87, 4968.872)
                ),
                // Paris
                Arguments.of(
                        new LonLatHeight(2.2770203,48.8589507, 0),
                        new Coordinate(4200.941, 167.04, 4780.253)
                ),
                // Berlin
                Arguments.of(
                        new LonLatHeight(13.2846496, 52.5069704, 0),
                        new Coordinate(3786.25, 893.961, 5037.337)
                ),
                // Washington DC
                Arguments.of(
                        new LonLatHeight(-77.0846159, 38.8937091, 0),
                        new Coordinate(1111.02, -4844.991, 3983.14)
                ),
                // Tokyo
                Arguments.of(
                        new LonLatHeight(139.5703039, 35.6735408, 0),
                        new Coordinate(-3948.513, 3363.979, 3698.826)
                ),
                // Seoul
                Arguments.of(
                        new LonLatHeight(126.7093638, 37.5652894, 0),
                        new Coordinate(-3025.836, 4058.086, 3867.31)
                ),
                // Cairo
                Arguments.of(
                        new LonLatHeight(31.2234449, 30.0595581, 0),
                        new Coordinate(4724.675, 2864.008, 3176.09)
                ),
                // Canberra
                Arguments.of(
                        new LonLatHeight(149.128684, -35.282000, 0),
                        new Coordinate(-4473.933, 2674.553, -3663.451)
                ),
                // Wellington
                Arguments.of(
                        new LonLatHeight(174.77557, -41.28664, 0),
                        new Coordinate(-4779.707, 437.042, -4186.396)
                ),
                // Buenos Aires
                Arguments.of(
                        new LonLatHeight(-58.381592, -34.603722, 0),
                        new Coordinate(2755.255, -4475.381, -3601.769)
                ),
                // Cape Town
                Arguments.of(
                        new LonLatHeight(18.423300, -33.918861, 0),
                        new Coordinate(5026.736, 1674.443, -3538.982)
                ),
                // Bangkok
                Arguments.of(
                        new LonLatHeight(100.523186, 13.736717, 0),
                        new Coordinate(-1131.756, 6092.649, 1504.702)
                )
        );
    }

}
