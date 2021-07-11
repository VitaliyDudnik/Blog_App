package com.tms.service;

import com.tms.entity.Post;
import com.tms.storage.post.DbPostStorage;

import java.util.List;

public class PostService {
    private final DbPostStorage dbPost = new DbPostStorage();

    public boolean add(Post post) {
        dbPost.save(post);
        return true;
    }

    public void updatePost(int id, String description) {
        dbPost.updatePost(id, description);
    }

    public List<Post> getAll() {
        return dbPost.findAll();
    }

    public void delete(int id) {
        dbPost.delete(id);
    }

    public boolean postDataPattern(String title, String description) {
        return dbPost.postDataPattern(title, description);
    }

    public boolean postDescriptionPattern(String description) {
        return dbPost.postDescriptionPattern(description);
    }

    public void approvePost(int id){
        dbPost.approved(id);
    }

}
