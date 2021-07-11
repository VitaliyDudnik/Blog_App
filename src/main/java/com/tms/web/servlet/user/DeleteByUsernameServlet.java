package com.tms.web.servlet.user;

import com.tms.entity.User;
import com.tms.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "deleteByUsername", urlPatterns = "/deleteAccount")
public class DeleteByUsernameServlet extends HttpServlet {
    private final UserService u = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) req.getSession().getAttribute("user");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            boolean valid = u.validPassUname(password, username);
            boolean matchUsername = user.getUsername().equalsIgnoreCase(username);
            boolean matchPassword = user.getPassword().equalsIgnoreCase(password);

            if (matchUsername & matchPassword & valid) {
                u.deleteByUsername(username);
                resp.getWriter().print("Your account has been successfully deleted");
                    session.invalidate();
            } else {
                resp.getWriter().print("Password or username is incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
