package com.tms.web.filter.user;

import com.tms.entity.Role;
import com.tms.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"DeleteUserServlet", "ApprovePostServlet", "ApproveCommentServlet"})
public class RoleFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = (User) req.getSession().getAttribute("user");
        if (user.getRole() != Role.ADMIN) {

            res.sendError(403);
        } else {
            chain.doFilter(req, res);
        }
    }
}
