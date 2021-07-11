package com.tms.web.servlet.comment;

import com.tms.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteCommentServlet", urlPatterns = "/deleteComment")
public class DeleteCommentServlet extends HttpServlet {
    private final CommentService commentService = new CommentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int commentId = Integer.parseInt(req.getParameter("id"));
            commentService.deleteComment(commentId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
