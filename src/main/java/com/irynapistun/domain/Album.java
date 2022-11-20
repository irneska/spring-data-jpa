package com.irynapistun.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Album {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "year_of_publishing")
    private Integer yearOfPublishing;
    @ManyToOne
    @JoinColumn(name = "musical_label_id", referencedColumnName = "id", nullable = false)
    private MusicalLabel musicalLabel;
    @OneToMany(mappedBy = "albums")
    private List<Musician> musicians;
    @OneToMany(mappedBy = "albums")
    private List<Song> songs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(Integer yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id) && Objects.equals(name, album.name) && Objects.equals(yearOfPublishing, album.yearOfPublishing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, yearOfPublishing);
    }

    public MusicalLabel getMusicalLabel() {
        return musicalLabel;
    }

    public void setMusicalLabel(MusicalLabel musicalLabel) {
        this.musicalLabel = musicalLabel;
    }

    public List<Musician> getMusicians() {
        return musicians;
    }

    public void setMusicians(List<Musician> musicians) {
        this.musicians = musicians;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
