package com.company.musicstorerecommendations.Repository;

import com.company.musicstorerecommendations.Model.LabelRecommendations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRecommendationsRepository extends JpaRepository<LabelRecommendations, Integer> {
}
