package com.example.demo.streaming.domain.video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdWatchHistoryRepository extends JpaRepository<AdWatchHistory, Integer> {

    @Query("SELECT COUNT(a) FROM AdWatchHistory a WHERE a.videoId = ?1")
    long countByVideoId(int videoId);
}
