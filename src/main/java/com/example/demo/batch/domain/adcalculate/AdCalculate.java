package com.example.demo.batch.domain.adcalculate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdCalculate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int adCalculateId;

    @Column(nullable = false)
    private int videoId;

    @Column(nullable = false)
    private int adCalculateAmount;
}