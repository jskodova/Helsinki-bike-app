package com.solita.bikeapp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JourneyTest {

    private Journey journeyUnderTest;

    @BeforeEach
    void setUp() {
        journeyUnderTest = new Journey(0, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                0, "depStationName", 0, "retStationName", 0.0f, 0);
    }

    @Test
    void testEquals() {
        assertNotEquals("o", journeyUnderTest);
    }

    @Test
    void testHashCode() {
        assertEquals(0, journeyUnderTest.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("result", journeyUnderTest.toString());
    }
}
