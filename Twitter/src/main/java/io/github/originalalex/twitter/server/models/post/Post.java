package io.github.originalalex.twitter.server.models.post;

import io.github.originalalex.twitter.server.models.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private Set<User> likers;

    @OneToMany
    private Set<User> retweeters;

    @OneToOne
    private User poster;

    private String content;
    private Date datePosted;

    @PrePersist
    public void createdAt() {
        this.datePosted = new Date();
    }

}
