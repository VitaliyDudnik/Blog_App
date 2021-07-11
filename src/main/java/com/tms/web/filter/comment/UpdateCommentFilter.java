package com.tms.web.filter.comment;

import com.tms.entity.Comment;
import com.tms.entity.User;
import com.tms.service.CommentService;

import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebFilter(servletNames = "UpdateCommentServlet")
public class UpdateCommentFilter extends HttpFilter {
  private final CommentService commentService = new CommentService();

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {

    User user = (User) req.getSession().getAttribute("user");
    int commentId = Integer.parseInt(req.getParameter("id"));
    String newText = req.getParameter("newText");

    try {
      List<Comment> commentList = new ArrayList<>(commentService.getComments());
      if (user != null & newText != null & !newText.trim().isEmpty() &
              commentList.stream().anyMatch(p -> p.getUser().getId() != user.getId()) &
              commentList.stream().anyMatch(s -> s.getId() != commentId)) {

        res.sendError(403);
      } else {
        chain.doFilter(req, res);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

