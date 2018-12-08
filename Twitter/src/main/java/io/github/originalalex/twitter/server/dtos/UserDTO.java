package io.github.originalalex.twitter.server.dtos;

import io.github.originalalex.twitter.server.models.post.Post;
import io.github.originalalex.twitter.server.models.user.User;

import java.util.Set;

public class UserDTO {

    private String username;
    private String profilePicUrl;
    private String bio;
    private String website;
    private Set<Post> posts;
    private Set<User> following;
    private Set<User> followers;
    private Set<Post> likedPosts;
    private Set<Post> retweetedPosts;

    public UserDTO(String username, String profilePicUrl, String bio, String website, Set<Post> posts, Set<User> following, Set<User> followers, Set<Post> likedPosts, Set<Post> retweetedPosts) {
        this.username = username;
        this.profilePicUrl = profilePicUrl;
        this.bio = bio;
        this.website = website;
        this.posts = posts;
        this.following = following;
        this.followers = followers;
        this.likedPosts = likedPosts;
        this.retweetedPosts = retweetedPosts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<Post> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(Set<Post> likedPosts) {
        this.likedPosts = likedPosts;
    }

    public Set<Post> getRetweetedPosts() {
        return retweetedPosts;
    }

    public void setRetweetedPosts(Set<Post> retweetedPosts) {
        this.retweetedPosts = retweetedPosts;
    }
}
