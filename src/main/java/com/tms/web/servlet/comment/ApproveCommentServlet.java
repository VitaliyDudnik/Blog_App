package com.tms.web.servlet.comment;

import com.tms.service.CommentService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ApproveCommentServlet", urlPatterns = "/approveComment")
public class ApproveCommentServlet extends HttpServlet {
    CommentService commentService = new CommentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int commentId = Integer.parseInt(req.getParameter("id"));
            commentService.approveComment(commentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
