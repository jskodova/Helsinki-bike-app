package com.solita.bikeapp.repository;

import com.solita.bikeapp.entity.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<StationEntity, Long> {
    @Query("SELECT j.depStationName, COUNT(j.depStationName) FROM JourneyEntity j GROUP BY j.depStationName")
    List<String[]> countUniqueDepStationName();

    @Query("SELECT j.retStationName, COUNT(j.retStationName) FROM JourneyEntity j GROUP BY j.retStationName")
    List<String[]> countUniqueRetStationName();
}
