package io.github.originalalex.twitter.server.models.user;

import io.github.originalalex.twitter.server.models.post.Conversation;
import io.github.originalalex.twitter.server.models.post.Post;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String passwordHash;

    private Date dateCreated;

    @OneToMany
    private Set<Post> posts;

    @ManyToMany
    private Set<Conversation> conversations;

    @ManyToMany
    private Set<User> following;

    @ManyToMany
    private Set<User> followers;

    @ManyToMany
    private Set<Post> likedPosts;

    @ManyToMany
    private Set<Post> retweetedPosts;

    private String profilePicURI;
    private String email;
    private String bio;
    private String website;

    public User(String username, String passwordHash, String email) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.posts = new HashSet<>();
        this.conversations = new HashSet<>();
        this.followers = new HashSet<>();
        this.followers = new HashSet<>();
        this.likedPosts = new HashSet<>();
        this.retweetedPosts = new HashSet<>();
    }

    public User() {}

    @PrePersist
    public void cretedAt() {
        this.dateCreated = new Date();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBio() {

        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicURI() {

        return profilePicURI;
    }

    public void setProfilePicURI(String profilePicURI) {
        this.profilePicURI = profilePicURI;
    }

    public Set<Post> getRetweetedPosts() {

        return retweetedPosts;
    }

    public void setRetweetedPosts(Set<Post> retweetedPosts) {
        this.retweetedPosts = retweetedPosts;
    }

    public Set<Post> getLikedPosts() {

        return likedPosts;
    }

    public void setLikedPosts(Set<Post> likedPosts) {
        this.likedPosts = likedPosts;
    }

    public Set<User> getFollowers() {

        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<Conversation> getConversations() {

        return conversations;
    }

    public void setConversations(Set<Conversation> conversations) {
        this.conversations = conversations;
    }

    public Set<Post> getPosts() {

        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Date getDateCreated() {

        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Set<User> getFollowing() {

        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }


}
