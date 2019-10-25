import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginServlet extends HttpServlet {

    UserDAO ud = new UserDAO();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        if (user != null) {
            response.sendRedirect("/profile");
        } else {
            try {
                if (validatingUser(request.getParameter("login"), request.getParameter("password"))) {
                    session.setAttribute("current_user", ud.getUserByLogin(request.getParameter("login")));
                    response.sendRedirect("/profile");
                } else {
                    response.sendRedirect("/login");
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("current_user");
        if (user != null) {
            response.sendRedirect("/profile");
        }
        else {
            response.setContentType("text/html");
            RequestDispatcher rd = request.getRequestDispatcher("/login_page");
            rd.forward(request, response);
        }
    }

    private boolean validatingUser(String login, String password) throws SQLException, ClassNotFoundException {
        Statement st = ConnectionToDatabase.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from users " +
                "where login = '" + login + "' AND password = '" + password + "'");
        return rs.next();
    }
}
