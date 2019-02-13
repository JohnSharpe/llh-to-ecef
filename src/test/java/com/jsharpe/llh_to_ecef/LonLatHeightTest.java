package com.jsharpe.llh_to_ecef;

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

}
