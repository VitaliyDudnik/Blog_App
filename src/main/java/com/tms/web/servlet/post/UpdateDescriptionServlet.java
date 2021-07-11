package com.tms.web.servlet.post;

import com.tms.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateDescriptionServlet", urlPatterns = "/updateDescription")
public class UpdateDescriptionServlet extends HttpServlet {
    PostService postService = new PostService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int postId = Integer.parseInt(req.getParameter("id"));
        String newDescription = req.getParameter("newDescription");
        try {
            boolean validDescription = postService.postDescriptionPattern(newDescription);
            if (newDescription != null & validDescription) {
                postService.updatePost(postId, newDescription);
            } else {
                resp.getWriter().print("New description is incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
