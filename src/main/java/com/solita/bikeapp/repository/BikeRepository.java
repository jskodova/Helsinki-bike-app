package com.solita.bikeapp.repository;

import com.solita.bikeapp.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike, Long> {

}