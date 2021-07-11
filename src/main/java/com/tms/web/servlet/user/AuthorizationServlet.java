package com.tms.web.servlet.user;

import com.tms.entity.User;
import com.tms.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "AuthorizationServlet", urlPatterns = "/authorization")
public class AuthorizationServlet extends HttpServlet {
    private final UserService u = new UserService();

    @Override()
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            User byUsername = u.getByUsername(username);
            boolean valid = u.validPassUname(password, username);
           if(byUsername!=null) {
               boolean equalPassword = byUsername.getPassword().equalsIgnoreCase(password);
            if (equalPassword & valid) {
                req.getSession().setAttribute("user", byUsername);
                resp.getWriter().print("Welcome back: " + username);

            }} else {
                resp.getWriter().print("Incorrect username or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

