package com.tms.web.servlet.post;

import com.tms.service.CommentService;
import com.tms.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeletePostServlet", urlPatterns = "/deletePost")
public class DeletePostServlet extends HttpServlet {
     private final PostService postService = new PostService();
     private final CommentService commentService = new CommentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.parseInt(req.getParameter("postId"));
        postService.delete(postId);
        commentService.deleteCommentByPostId(postId);

    }
}
