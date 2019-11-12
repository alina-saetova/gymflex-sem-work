package servlets;

import dao.UserDAO;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


@MultipartConfig
public class RegistrationServlet extends HttpServlet {

    UserDAO ud = new UserDAO();
    UserService us = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String name = request.getParameter("name");
        resp.setContentType("text/html");
        request.setAttribute("name", name);
        RequestDispatcher rd = request.getRequestDispatcher("/registration_page");
        rd.forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        Cookie c = new Cookie("user_login", login);
        c.setMaxAge(60 * 60 * 24 * 14);
        resp.addCookie(c);
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String password = null;
        try {
            password = us.makeDigest(req.getParameter("password"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Part p = req.getPart("photo");
        String localdir = "uploads";
        String pathDir = getServletContext().getRealPath("") + File.separator + localdir;
        File dir = new File(pathDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String[] filename_data = p.getSubmittedFileName().split("\\.");
        String filename = Math.random() + "." + filename_data[filename_data.length - 1];
        String fullpath = pathDir + File.separator + filename;
        p.write(fullpath);
        String photo = "/" + localdir + "/" + filename;

        try {
            ud.insert(firstName, lastName, login, password, photo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            req.getSession().setAttribute("current_user", ud.getUserByLogin(login));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/profile");
    }
}
