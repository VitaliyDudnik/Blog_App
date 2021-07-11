package com.tms.web.servlet.user;

import com.tms.entity.User;
import com.tms.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UpdateUsernameServlet", urlPatterns = "/updateUsername")
public class UpdateUsernameServlet extends HttpServlet {
    UserService u = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        int id = Integer.parseInt(req.getParameter("userId"));
        String newUsername = req.getParameter("name");
        User user = (User) req.getSession().getAttribute("user");
        boolean exist = u.existById(id);
        boolean validUname = u.validUname(newUsername);
        try {

            if (user != null & validUname & exist) {
                u.updateUsername(user, newUsername);
                resp.getWriter().print("Username has been successfully updated. New username: " + newUsername);
                resp.getWriter().print("Please re enter in the system");
                session.invalidate();
            } else {
                resp.getWriter().print("New username is incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
