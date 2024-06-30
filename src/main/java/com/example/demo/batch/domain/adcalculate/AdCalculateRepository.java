package com.example.demo.batch.domain.adcalculate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdCalculateRepository extends JpaRepository<AdCalculate, Integer> {
}
