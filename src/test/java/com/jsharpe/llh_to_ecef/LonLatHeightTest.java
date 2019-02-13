package com.jsharpe.llh_to_ecef;

import com.jsharpe.llh_to_ecef.dto.Coordinate;
import com.jsharpe.llh_to_ecef.dto.LonLatHeight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LonLatHeightTest {

    @Test
    void testIdenticalLonLatHeight() {
        // Given
        final LonLatHeight lonLatHeightA = new LonLatHeight(1, 2, 3);
        final LonLatHeight lonLatHeightB = new LonLatHeight(1, 2, 3);

        // When
        final boolean equalA = lonLatHeightA.equals(lonLatHeightB);
        final boolean equalB = lonLatHeightB.equals(lonLatHeightA);

        final int hashA = lonLatHeightA.hashCode();
        final int hashB = lonLatHeightB.hashCode();

        // Then
        Assertions.assertTrue(equalA);
        Assertions.assertTrue(equalB);

        // Must hold true!
        Assertions.assertEquals(hashA, hashB);
    }

    @Test
    void testDifferentCoordinates() {
        // Given
        final LonLatHeight lonLatHeightA = new LonLatHeight(1, 2, 3);
        final LonLatHeight lonLatHeightB = new LonLatHeight(4, 5, 6);

        // When
        final boolean equalA = lonLatHeightA.equals(lonLatHeightB);
        final boolean equalB = lonLatHeightB.equals(lonLatHeightA);

        final int hashA = lonLatHeightA.hashCode();
        final int hashB = lonLatHeightB.hashCode();

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
        final LonLatHeight lonLatHeight = new LonLatHeight(1, 2, 3);

        // When
        final boolean equals = lonLatHeight.equals(lonLatHeight);

        // Then
        Assertions.assertTrue(equals);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void testWithNull() {
        // Given
        final LonLatHeight lonLatHeight = new LonLatHeight(1, 2, 3);

        // When
        final boolean equals = lonLatHeight.equals(null);

        // Then
        Assertions.assertFalse(equals);
    }

    @SuppressWarnings({"ConstantConditions", "EqualsBetweenInconvertibleTypes"})
    @Test
    void testWithDifferentClass() {
        // Given
        final LonLatHeight lonLatHeight = new LonLatHeight(1, 2, 3);
        final Coordinate coordinate = new Coordinate(1, 2, 3);

        // When
        final boolean equals = lonLatHeight.equals(coordinate);

        // Then
        Assertions.assertFalse(equals);
    }

}
