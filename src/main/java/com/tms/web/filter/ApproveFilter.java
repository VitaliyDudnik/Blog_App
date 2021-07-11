package com.tms.web.filter;

import com.tms.entity.Role;
import com.tms.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(servletNames = {"ApprovePostServlet", "ApproveCommentServlet"})
public class ApproveFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
        try {
            User user = (User) req.getSession().getAttribute("user");
            String id = req.getParameter("id");

            if (user.getRole() != Role.ADMIN &
                    id.trim().isEmpty()) {
                res.sendError(403);

            } else {
                chain.doFilter(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
