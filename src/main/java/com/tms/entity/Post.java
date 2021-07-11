package com.tms.entity;

import java.util.List;
import java.util.Objects;

public class Post {
    private int id;
    private String title;
    private String description;
    private User user;
    private List<Comment> comments;
    private boolean approved;

    public Post(int id, String title, String description, User user, List<Comment> comments,boolean approved) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.comments = comments;
        this.approved = approved;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && approved == post.approved && Objects.equals(title, post.title) && Objects.equals(description, post.description) && Objects.equals(user, post.user) && Objects.equals(comments, post.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, user, comments, approved);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", comments=" + comments +
                ", approved=" + approved +
                '}';
    }
}
