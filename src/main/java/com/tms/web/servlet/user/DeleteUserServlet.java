package com.tms.web.servlet.user;

import com.tms.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteUserServlet", urlPatterns = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private final UserService u = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            boolean existById = u.existById(id);
            if (existById) {
                u.deleteById(id);
            } else {
                resp.getWriter().print("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}