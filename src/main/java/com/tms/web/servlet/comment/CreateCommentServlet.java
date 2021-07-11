package com.tms.web.servlet.comment;

import com.tms.entity.Comment;
import com.tms.entity.User;
import com.tms.service.CommentService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CreateCommentServlet", urlPatterns = "/createComment")
public class CreateCommentServlet extends HttpServlet {
    CommentService commentService = new CommentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        String text = req.getParameter("text");
        int userId = Integer.parseInt(req.getParameter("userId"));
        int postId = Integer.parseInt(req.getParameter("postId"));

        try {
            User user = (User) req.getSession().getAttribute("user");
            Comment comment = new Comment(0, postId, text, user, false);

            boolean validComment = commentService.commentPattern(text);
            assert user != null;

            if (validComment) {
                commentService.add(comment, text, userId, postId);
                resp.getWriter().print("Comment: " + text + " sent for confirmation");
            } else {
                resp.getWriter().print("Comment does not match our rules.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
