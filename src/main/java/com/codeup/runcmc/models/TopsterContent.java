package com.codeup.runcmc.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "topster_contents")
public class TopsterContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="topster_id")
    private Topster topster;

    @Column
    private long spotifyAlbumID;

    @Column
    private String spotifyAlbumName;

    @Column
    private String spotifyAlbumArt;

    @Column
    private String spotifyArtist;

    @Column
    private Date releaseDate;

    @Column
    private String genre;

    @Column
    private int position;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Topster getTopster() {
        return topster;
    }

    public void setTopster(Topster topster) {
        this.topster = topster;
    }

    public long getSpotifyAlbumID() {
        return spotifyAlbumID;
    }

    public void setSpotifyAlbumID(long spotifyAlbumID) {
        this.spotifyAlbumID = spotifyAlbumID;
    }

    public String getSpotifyAlbumName() {
        return spotifyAlbumName;
    }

    public void setSpotifyAlbumName(String spotifyAlbumName) {
        this.spotifyAlbumName = spotifyAlbumName;
    }

    public String getSpotifyAlbumArt() {
        return spotifyAlbumArt;
    }

    public void setSpotifyAlbumArt(String spotifyAlbumArt) {
        this.spotifyAlbumArt = spotifyAlbumArt;
    }

    public String getSpotifyArtist() {
        return spotifyArtist;
    }

    public void setSpotifyArtist(String spotifyArtist) {
        this.spotifyArtist = spotifyArtist;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
