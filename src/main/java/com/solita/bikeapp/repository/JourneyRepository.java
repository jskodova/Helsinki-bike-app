package com.solita.bikeapp.repository;

import com.solita.bikeapp.entity.JourneyEntity;
import com.solita.bikeapp.model.Journey;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyRepository extends JpaRepository<JourneyEntity, Long> {
    List<Journey> findByDepStationName(String depStationName, Pageable pageable);

    List<Journey> findByRetStationName(String retStationName, Pageable pageable);

    List<Journey> findByJourneyID(Integer journeyID);

    List<Journey> findByRetStationID(Integer retStationID);

    List<Journey> findByDepStationID(Integer depStationID, Pageable pageable);

    List<Journey> findByDuration(Integer duration, Pageable pageable);

    List<Journey> findByDistance(Float distance, Pageable pageable);
}