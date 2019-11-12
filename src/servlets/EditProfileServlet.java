package servlets;

import dao.UserDAO;
import models.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditProfileServlet extends HttpServlet {

    UserDAO ud = new UserDAO();
    UserService us = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        User user = (User) req.getSession().getAttribute("current_user");
        try {
            us.updateProfile(firstname, lastname, login, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            req.getSession().setAttribute("current_user", ud.getUserById(user.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
