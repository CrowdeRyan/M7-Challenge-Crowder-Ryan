package com.company.musicstorerecommendations.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "label_recommendation")
public class LabelRecommendations {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "label_recommendation_id")
        private Integer id;
        private int label_id;
        private int user_id;
        private boolean liked;

    public LabelRecommendations(Integer id, int label_id, int user_id, boolean liked) {
        this.id = id;
        this.label_id = label_id;
        this.user_id = user_id;
        this.liked = liked;
    }

    public LabelRecommendations() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLabel_id() {
        return label_id;
    }

    public void setLabel_id(int label_id) {
        this.label_id = label_id;
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
        LabelRecommendations that = (LabelRecommendations) o;
        return label_id == that.label_id && user_id == that.user_id && liked == that.liked && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label_id, user_id, liked);
    }

    @Override
    public String toString() {
        return "LabelRecommendations{" +
                "id=" + id +
                ", label_id=" + label_id +
                ", user_id=" + user_id +
                ", liked=" + liked +
                '}';
    }
}
