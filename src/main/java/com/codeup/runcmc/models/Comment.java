package com.codeup.runcmc.models;

import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long userID;

    @Column
    private long topsterID;
    @Column
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "topster_id")
    private Topster topster;

    public Comment(long id, long userID, long topsterID, String body) {
        this.id = id;
        this.userID = userID;
        this.topsterID = topsterID;
        this.body = body;
    }

    public Comment(long userID, long topsterID, String body) {
        this.userID = userID;
        this.topsterID = topsterID;
        this.body = body;
    }

    public Comment(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getTopsterID() {
        return topsterID;
    }

    public void setTopsterID(long topsterID) {
        this.topsterID = topsterID;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topster getTopster() {
        return topster;
    }

    public void setTopster(Topster topster) {
        this.topster = topster;
    }
}
