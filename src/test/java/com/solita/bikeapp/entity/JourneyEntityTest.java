package com.solita.bikeapp.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class JourneyEntityTest {

    private JourneyEntity journeyEntityUnderTest;

    @BeforeEach
    void setUp() {
        journeyEntityUnderTest = new JourneyEntity();
    }

    @Test
    void testEquals() {
        assertNotEquals("o", journeyEntityUnderTest);
    }

}
