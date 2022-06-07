package com.company.musicstorecatalog.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Integer id;
    private String title;
    private String artist_id;
    private String release_date;
    private int label_id;
    private int list_price;

    public Album() {
    }

    public Album(Integer id, String title, String artist_id, String release_date, int label_id, int list_price) {
        this.id = id;
        this.title = title;
        this.artist_id = artist_id;
        this.release_date = release_date;
        this.label_id = label_id;
        this.list_price = list_price;
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

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getLabel_id() {
        return label_id;
    }

    public void setLabel_id(int label_id) {
        this.label_id = label_id;
    }

    public int getList_price() {
        return list_price;
    }

    public void setList_price(int list_price) {
        this.list_price = list_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return id == album.id && label_id == album.label_id && list_price == album.list_price && Objects.equals(title, album.title) && Objects.equals(artist_id, album.artist_id) && Objects.equals(release_date, album.release_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, artist_id, release_date, label_id, list_price);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist_id='" + artist_id + '\'' +
                ", release_date='" + release_date + '\'' +
                ", label_id=" + label_id +
                ", list_price=" + list_price +
                '}';
    }
}
