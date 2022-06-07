package com.company.musicstorerecommendations.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "track_recommendation")
public class TrackRecommendations {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "track_recommendation_id")
        private Integer id;
        private int track_id;
        private int user_id;
        private boolean liked;

    public TrackRecommendations(Integer id, int track_id, int user_id, boolean liked) {
        this.id = id;
        this.track_id = track_id;
        this.user_id = user_id;
        this.liked = liked;
    }

    public TrackRecommendations() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTrack_id() {
        return track_id;
    }

    public void setTrack_id(int track_id) {
        this.track_id = track_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackRecommendations that = (TrackRecommendations) o;
        return track_id == that.track_id && user_id == that.user_id && liked == that.liked && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, track_id, user_id, liked);
    }

    @Override
    public String toString() {
        return "TrackRecommendations{" +
                "id=" + id +
                ", track_id=" + track_id +
                ", user_id=" + user_id +
                ", liked=" + liked +
                '}';
    }
}
