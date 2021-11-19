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
    private List<TopsterContent> albums; //each 'TopsterContent' is essentially an album. Maybe this is more confusing?

//    @ManyToMany
//    @JoinTable(name = "favorites",
//            joinColumns = { @JoinColumn(name = "fk_topster") },
//            inverseJoinColumns = { @JoinColumn(name = "fk_user") })
//    private User  = new HashSet<Product>();

    @Column
    private String title;

    @Column
    private String body;

    @Column
    private boolean isPublic;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
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

    public List<TopsterContent> getAlbums() {
        return albums;
    }

    public void setAlbums(List<TopsterContent> albums) {
        this.albums = albums;
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

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
