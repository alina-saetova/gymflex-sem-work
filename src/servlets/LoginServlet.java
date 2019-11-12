package servlets;

import dao.UserDAO;
import models.User;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    UserDAO ud = new UserDAO();
    UserService us = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        String login = request.getParameter("login");
        System.out.println(login + request.getParameter("password"));
        response.setContentType("text/html");
        if (request.getParameter("remember").equals("check")) {
            Cookie c = new Cookie("user_login", login);
            c.setMaxAge(60 * 60 * 24 * 14);
            response.addCookie(c);
        }
        try {
            if (us.validatingUser(login, request.getParameter("password"))) {
                session.setAttribute("current_user", ud.getUserByLogin(login));
                response.getWriter().println(1);

            } else {
                response.getWriter().println(0);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher rd = request.getRequestDispatcher("/login_page");
        rd.forward(request, response);
    }

}
