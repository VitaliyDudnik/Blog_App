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
import java.util.stream.Collectors;

@WebFilter(servletNames = "DeletePostServlet")
public class DeletePostFilter extends HttpFilter {
   private final PostService postService = new PostService();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        try {
            List<Post> postList = new ArrayList<>(postService.getAll());
            User user = (User) req.getSession().getAttribute("user");
            int postId = Integer.parseInt(req.getParameter("postId"));

            if (user != null & postList.stream().anyMatch(p -> p.getUser().getId() == user.getId() & postList.stream().anyMatch(s -> s.getId() == postId))) {
                chain.doFilter(req, res);
            }else {
                res.sendError(403);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
