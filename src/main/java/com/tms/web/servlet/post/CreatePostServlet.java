package com.tms.web.servlet.post;

import com.tms.entity.Post;
import com.tms.entity.User;
import com.tms.service.PostService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CreatePostServlet", urlPatterns = "/createPost")
public class CreatePostServlet extends HttpServlet {
    private final PostService postService = new PostService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        String description = req.getParameter("description");
        String title = req.getParameter("title");
        boolean validPostData = postService.postDataPattern(title, description);
        try {
            if(user!=null) {
                Post post = new Post(0, title, description, user, null, false);
            if (validPostData & post != null) {
                postService.add(post);
                resp.getWriter().print("Post: " + title + " sent for confirmation");
            }} else {
                resp.getWriter().print("Post does not match our rules.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

