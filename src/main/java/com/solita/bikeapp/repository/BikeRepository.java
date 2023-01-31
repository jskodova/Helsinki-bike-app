package com.solita.bikeapp.repository;

import com.solita.bikeapp.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer>{
}