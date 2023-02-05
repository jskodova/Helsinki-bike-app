package com.solita.bikeapp.repository;

import com.solita.bikeapp.entity.JourneyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends JpaRepository<JourneyEntity, Long> {

}