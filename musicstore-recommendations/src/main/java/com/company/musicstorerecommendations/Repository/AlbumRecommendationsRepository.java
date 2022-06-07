package com.company.musicstorerecommendations.Repository;

import com.company.musicstorerecommendations.Model.AlbumRecommendations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRecommendationsRepository extends JpaRepository<AlbumRecommendations, Integer> {
}
