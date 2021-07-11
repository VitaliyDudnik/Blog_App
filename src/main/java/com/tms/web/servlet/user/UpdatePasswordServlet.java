package com.tms.web.servlet.user;

import com.tms.entity.User;
import com.tms.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UpdatePasswordServlet", urlPatterns = "/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
    UserService u = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String password = req.getParameter("password");
        String newPassword = req.getParameter("newPassword");
        try {
            User user = (User) req.getSession().getAttribute("user");
            boolean validPass = u.validPass(password);
            boolean validNewPass = u.validPass(newPassword);

            if (validPass & validNewPass & user != null) {
                u.updatePassword(user, newPassword);
                resp.getWriter().print("Password has been successfully updated: " + newPassword);
                session.invalidate();
                resp.getWriter().print("Please re enter in the system");
            } else {
                resp.getWriter().print("Password or new password is incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
