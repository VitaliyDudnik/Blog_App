package com.tms.entity;

import java.util.Objects;

public class Comment {
    private int id;
    private int postId;
    private String text;
    private User user;
    private int userId;
    private boolean approved;

    public Comment(int id, int postId, String text, int userId, boolean approved) {
        this.id = id;
        this.postId = postId;
        this.text = text;
        this.userId = userId;
        this.approved = approved;
    }

    public Comment(int id, int postId, String text, User user, boolean approved) {
        this.id = id;
        this.postId = postId;
        this.text = text;
        this.user = user;
        this.approved = approved;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && postId == comment.postId && userId == comment.userId && approved == comment.approved && Objects.equals(text, comment.text) && Objects.equals(user, comment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postId, text, user, userId, approved);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postId=" + postId +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", userId=" + userId +
                ", approved=" + approved +
                '}';
    }
}