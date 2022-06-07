package com.company.musicstorecatalog.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id")
    private Integer id;
    private String title;
    private String album_id;
    private String run_time;

    public Track(Integer id, String title, String album_id, String run_time) {
        this.id = id;
        this.title = title;
        this.album_id = album_id;
        this.run_time = run_time;
    }

    public Track() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getRun_time() {
        return run_time;
    }

    public void setRun_time(String run_time) {
        this.run_time = run_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return id == track.id && Objects.equals(title, track.title) && Objects.equals(album_id, track.album_id) && Objects.equals(run_time, track.run_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, album_id, run_time);
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", album_id='" + album_id + '\'' +
                ", run_time='" + run_time + '\'' +
                '}';
    }
}

