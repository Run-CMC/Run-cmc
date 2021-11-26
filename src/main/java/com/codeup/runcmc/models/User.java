package com.codeup.runcmc.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    @Column (unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String bio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Topster> topsters;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(name = "favorites",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "topster_id") })
    private List<Topster> favoritedTopsters;

    public User(String username, String email, String password, String bio) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio= bio;
    }

    public User(long id, String username, String email, String password, String bio) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio = bio;
    }
    public User(){}

    // Do we need this?
//    public User(User copy) {
//        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
//        email = copy.email;
//        username = copy.username;
//        password = copy.password;
//    }

    public User(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.bio = user.getBio();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public String getBio() {
        return bio;
    }

    public List<Topster> getFavoritedTopsters() {
        return favoritedTopsters;
    }

    public void setFavoritedTopsters(List<Topster> favoritedTopsters) {
        this.favoritedTopsters = favoritedTopsters;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Topster> getTopsters() {
        return topsters;
    }

    public void setTopsters(List<Topster> topsters) {
        this.topsters = topsters;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
