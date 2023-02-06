package com.solita.bikeapp.entity;

import org.junit.jupiter.api.BeforeEach;

class StationEntityTest {

    private StationEntity stationEntityUnderTest;

    @BeforeEach
    void setUp() {
        stationEntityUnderTest = new StationEntity(0, "stationName", "address", "city", 0.0f, 0.0f, 0, 0, 0.0, 0.0);
    }
}
