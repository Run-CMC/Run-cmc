package com.codeup.runcmc.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="topsters")
public class Topster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topster")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topster")
    private List<TopsterContent> topsterContents; //each 'TopsterContent' is essentially an album. Maybe this is more confusing?

    @ManyToMany(mappedBy = "favoritedTopsters")
    private List<User> usersWhoFavorited;

    @Column
    @NotBlank(message = "Topsters must have a title")
    @Size(min = 3, message = "A title must have at least 3 characters.")
    private String title;

    @Column
    @NotBlank(message = "Topsters must have a description")
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
