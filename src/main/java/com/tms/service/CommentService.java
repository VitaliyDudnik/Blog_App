package com.tms.service;

import com.tms.DataPattern.CommentPattern;
import com.tms.entity.Comment;
import com.tms.entity.Post;
import com.tms.storage.comment.DbCommentStorage;

import java.util.List;

public class CommentService {
    private final DbCommentStorage dbComment = new DbCommentStorage();
    private final CommentPattern cPattern = new CommentPattern();


    public boolean add(Comment comment, String text, int userid, int postId) {
        dbComment.save(comment, text, userid, postId);
        return true;
    }

    public List<Comment> getAll(int postId) {
        return dbComment.getCommentList(new Post(postId, null, null, null, null, true));
    }

    public List<Comment> getComments() {
        return dbComment.getComments();
    }

    public Comment getComment(int id){
        dbComment.getCommentById(id);
        return null;
    }

    public void updateComment(int id, String text) {
        dbComment.updateComment(id, text);
    }

    public void deleteComment(int id) {
        dbComment.delete(id);
    }

    public  void deleteCommentByPostId(int postId){
        dbComment.deleteByPostId(postId);
    }

    public void approveComment(int id) {
        dbComment.approved(id);
    }

    public boolean commentPattern(String text){
        return cPattern.commentPattern(text);
    }

}
