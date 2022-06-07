package com.company.musicstorerecommendations.Repository;

import com.company.musicstorerecommendations.Model.TrackRecommendations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRecommendationsRepository extends JpaRepository<TrackRecommendations, Integer> {
}
