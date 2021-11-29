package com.codeup.runcmc.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
    private List<TopsterContent> topsterContents;

    @Column (name= "spotify_album_id")
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

    public List<TopsterContent> getTopsterContents() {
        return topsterContents;
    }

    public void setTopsterContents(List<TopsterContent> topsterContents) {
        this.topsterContents = topsterContents;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

}

