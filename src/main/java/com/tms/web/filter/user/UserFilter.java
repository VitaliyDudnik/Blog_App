package com.tms.web.filter.user;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"LogoutServlet", "UpdatePasswordServlet", "UpdateUsernameServlet", "CreatePostServlet",
        "CreateCommentServlet", "deleteByUsername", "DeletePostServlet", "DeleteCommentServlet"})
public class UserFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (req.getSession().getAttribute("user") == null) {
            res.sendError(403);
        } else{
            chain.doFilter(req, res);
        }
    }
}


