package com.codeup.runcmc.models;

import com.codeup.runcmc.services.AlbumJsonDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
import java.util.Map;

//@JsonIgnoreProperties(ignoreUnknown = true)
//public class AlbumResponse {
//    @JsonProperty("album_art")
//    private String albumArt;
////    @JsonProperty("artists")
////    private String[] artists;
//    @JsonProperty("name")
//    private String albumName;
//    @JsonProperty("release_date")
//    private String releaseDate;

@JsonDeserialize(using = AlbumJsonDeserializer.class)
public class AlbumResponse {
    private String albumArt;
    private String albumName;
    private List<String> artists;
    private String artist;
    private String releaseDate;

    public AlbumResponse(String albumArt, String albumName, List<String> artists, String releaseDate) {
        this.albumArt = albumArt;
        this.albumName = albumName;
        this.artists = artists;
        this.releaseDate = releaseDate;
    }

    public AlbumResponse(String albumArt, String albumName, String artist, String releaseDate) {
        this.albumArt = albumArt;
        this.albumName = albumName;
        this.artist = artist;
        this.releaseDate = releaseDate;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtist() {
        return artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }


    @SuppressWarnings("unchecked")
    @JsonProperty("artists")
    private void unpackNested(Map<String, Object> artists) {
        this.artist = (String)artists.get("name");
    }

}
