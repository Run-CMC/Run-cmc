package com.codeup.runcmc.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="topsters")
public class Topster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topster")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topster")
    private List<TopsterContent> topsterContents; //each 'TopsterContent' is essentially an album. Maybe this is more confusing?

    @ManyToMany
    @JoinTable(name = "favorites",
            joinColumns = { @JoinColumn(name = "topster_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private List<User> usersWhoFavorited;

    @Column
    private String title;

    @Column
    private String body;

    @Column
    private Boolean publicity;

    public User getUser() {
        return user;
    }

    public List<TopsterContent> getTopsterContents() {
        return topsterContents;
    }

    public void setTopsterContents(List<TopsterContent> topsterContents) {
        this.topsterContents = topsterContents;
    }

    public List<User> getUsersWhoFavorited() {
        return usersWhoFavorited;
    }

    public void setUsersWhoFavorited(List<User> usersWhoFavorited) {
        this.usersWhoFavorited = usersWhoFavorited;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean isPublic() {
        return publicity;
    }

    public void setPublic(Boolean isPublic) {
        this.publicity = isPublic;
    }
}
