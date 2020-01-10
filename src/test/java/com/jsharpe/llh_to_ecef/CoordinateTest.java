package com.jsharpe.llh_to_ecef;

import com.jsharpe.llh_to_ecef.dto.Coordinate;
import com.jsharpe.llh_to_ecef.dto.LonLatHeight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CoordinateTest {

    @Test
    void testIdenticalCoordinates() {
        // Given
        final Coordinate coordinateA = new Coordinate(1, 2, 3);
        final Coordinate coordinateB = new Coordinate(1, 2, 3);

        // When
        final boolean equalA = coordinateA.equals(coordinateB);
        final boolean equalB = coordinateB.equals(coordinateA);

        final int hashA = coordinateA.hashCode();
        final int hashB = coordinateB.hashCode();

        // Then
        Assertions.assertTrue(equalA);
        Assertions.assertTrue(equalB);

        // Must hold true!
        Assertions.assertEquals(hashA, hashB);
    }

    @Test
    void testDifferentCoordinates() {
        // Given
        final Coordinate coordinateA = new Coordinate(1, 2, 3);
        final Coordinate coordinateB = new Coordinate(4, 5, 6);

        // When
        final boolean equalA = coordinateA.equals(coordinateB);
        final boolean equalB = coordinateB.equals(coordinateA);

        final int hashA = coordinateA.hashCode();
        final int hashB = coordinateB.hashCode();

        // Then
        Assertions.assertFalse(equalA);
        Assertions.assertFalse(equalB);

        // Should hold true!
        Assertions.assertNotEquals(hashA, hashB);
    }

    @SuppressWarnings("EqualsWithItself")
    @Test
    void testWithSelf() {
        // Given
        final Coordinate coordinate = new Coordinate(1, 2, 3);

        // When
        final boolean equals = coordinate.equals(coordinate);

        // Then
        Assertions.assertTrue(equals);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void testWithNull() {
        // Given
        final Coordinate coordinate = new Coordinate(1, 2, 3);

        // When
        final boolean equals = coordinate.equals(null);

        // Then
        Assertions.assertFalse(equals);
    }

    @SuppressWarnings({"EqualsBetweenInconvertibleTypes"})
    @Test
    void testWithDifferentClass() {
        // Given
        final Coordinate coordinate = new Coordinate(1, 2, 3);
        final LonLatHeight lonLatHeight = new LonLatHeight(1, 2, 3);

        // When
        final boolean equals = coordinate.equals(lonLatHeight);

        // Then
        Assertions.assertFalse(equals);
    }

}
