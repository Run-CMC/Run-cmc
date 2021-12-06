package com.codeup.runcmc.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
        this.body = body;
    }

    public Comment(long userID, long topsterID, String body) {
        this.body = body;
    }

    public Comment(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

//    @Query(nativeQuery = true, value =  "SET foreign_key_checks = 0; UPDATE comments SET topster_id = :t;")
//    public void setTopster(@Param("t") long id) {
//    }
}
