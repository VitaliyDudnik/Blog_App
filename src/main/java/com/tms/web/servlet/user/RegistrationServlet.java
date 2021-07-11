package com.tms.web.servlet.user;

import com.tms.entity.Role;
import com.tms.entity.User;
import com.tms.service.UserService;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "/RegistrationServlet", urlPatterns = "/reg")
public class RegistrationServlet extends HttpServlet {
    private final UserService u = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(0, name, username, password, Role.USER);
        boolean validUserData = u.validUserData(name, username, password);

        try {
            if (u.findByUsername(username)) {
                resp.getWriter().print("This name is already exist.");
                return;
            }
            boolean add = u.add(user);
            if ( add & validUserData) {
                resp.getWriter().print("Registration succeeded");
                req.getSession().setAttribute("user", u.getByUsername(username));

            } else {
                resp.getWriter().print("Input data is incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

