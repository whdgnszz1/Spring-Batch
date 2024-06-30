package com.example.demo.batch.service;

import com.example.demo.batch.domain.adcalculate.AdCalculate;
import com.example.demo.batch.domain.adcalculate.AdCalculateRepository;
import com.example.demo.streaming.domain.video.AdWatchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BatchProcessingService {

    @Autowired
    private AdWatchHistoryRepository adWatchHistoryRepository;

    @Autowired
    private AdCalculateRepository adCalculateRepository;

    @Transactional
    public void calculateAdWatchCounts() {
        int[] videoIds = {1, 2, 3};

        for (int videoId : videoIds) {
            long count = adWatchHistoryRepository.countByVideoId(videoId);
            AdCalculate adCalculate = new AdCalculate();
            adCalculate.setVideoId(videoId);
            adCalculate.setAdCalculateAmount((int) count);
            adCalculateRepository.save(adCalculate);
        }
    }
}
