package com.tms.web.filter.post;

import com.tms.entity.Post;
import com.tms.entity.User;
import com.tms.service.PostService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(servletNames = "UpdateDescriptionServlet")
public class UpdateDescriptionFilter extends HttpFilter {
    private final PostService postService = new PostService();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        List<Post> postList = new ArrayList<>(postService.getAll());

        User user = (User) req.getSession().getAttribute("user");
        int postId = Integer.parseInt(req.getParameter("id"));
        String newDescription = req.getParameter("newDescription");

        if (user != null & newDescription != null & !newDescription.trim().isEmpty() &
                postList.stream().anyMatch(p -> p.getUser().getId() != user.getId()) &
                postList.stream().anyMatch(s -> s.getId() != postId)) {
            res.sendError(403);
        } else {
            chain.doFilter(req, res);
        }
    }
}

