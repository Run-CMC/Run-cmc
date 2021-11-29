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
    @JoinColumn(name = "topster_id")
    private Topster topster;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;


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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}