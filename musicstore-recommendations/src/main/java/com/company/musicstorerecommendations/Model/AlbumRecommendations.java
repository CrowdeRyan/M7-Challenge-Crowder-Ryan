package com.company.musicstorerecommendations.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "album_recommendation")
public class AlbumRecommendations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_recommendation_id")
    private Integer id;
    private int album_id;
    private int user_id;
    private boolean liked;

    public AlbumRecommendations(Integer id, int album_id, int user_id, boolean liked) {
        this.id = id;
        this.album_id = album_id;
        this.user_id = user_id;
        this.liked = liked;
    }

    public AlbumRecommendations() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
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
        AlbumRecommendations that = (AlbumRecommendations) o;
        return album_id == that.album_id && user_id == that.user_id && liked == that.liked && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, album_id, user_id, liked);
    }

    @Override
    public String toString() {
        return "AlbumRecommendations{" +
                "id=" + id +
                ", album_id=" + album_id +
                ", user_id=" + user_id +
                ", liked=" + liked +
                '}';
    }
}
