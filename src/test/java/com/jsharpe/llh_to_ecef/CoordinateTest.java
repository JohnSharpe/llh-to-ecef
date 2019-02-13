package com.jsharpe.llh_to_ecef;

import com.jsharpe.llh_to_ecef.dto.Coordinate;
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

}
