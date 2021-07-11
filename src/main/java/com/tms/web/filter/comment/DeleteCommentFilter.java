package com.tms.web.filter.comment;

import com.tms.entity.Comment;
import com.tms.entity.User;
import com.tms.service.CommentService;

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

@WebFilter(servletNames = "DeleteCommentServlet")
public class DeleteCommentFilter extends HttpFilter {
    private final CommentService commentService = new CommentService();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
        User user = (User) req.getSession().getAttribute("user");
        int commentId = Integer.parseInt(req.getParameter("id"));
        try {
            List<Comment> commentList = new ArrayList<>(commentService.getAll(commentId));

            if (user != null &
                    commentList.stream().anyMatch(c -> c.getUser().getId() == user.getId()) &
                    commentList.stream().anyMatch(s -> s.getId() == commentId)) {
                chain.doFilter(req, res);
            } else {
                res.sendError(403);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
