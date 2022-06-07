package com.company.musicstorerecommendations.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "artist_recommendation")
public class ArtistRecommendations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_recommendation_id")
    private Integer id;
    private int artist_id;
    private int user_id;
    private boolean liked;

    public ArtistRecommendations(Integer id, int artist_id, int user_id, boolean liked) {
        this.id = id;
        this.artist_id = artist_id;
        this.user_id = user_id;
        this.liked = liked;
    }

    public ArtistRecommendations() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
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
        ArtistRecommendations that = (ArtistRecommendations) o;
        return artist_id == that.artist_id && user_id == that.user_id && liked == that.liked && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artist_id, user_id, liked);
    }

    @Override
    public String toString() {
        return "ArtistRecommendations{" +
                "id=" + id +
                ", artist_id=" + artist_id +
                ", user_id=" + user_id +
                ", liked=" + liked +
                '}';
    }
}

