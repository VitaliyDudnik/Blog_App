package com.tms.web.servlet.comment;

import com.tms.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateCommentServlet", urlPatterns = "/updateComment")
public class UpdateCommentServlet extends HttpServlet {
    CommentService commentService = new CommentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String newText = req.getParameter("newText");
            assert newText != null;
            commentService.updateComment(id, newText);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
