import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class ChangePasswordServlet extends HttpServlet {

    UserDAO ud = new UserDAO();
    UserService us = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldpassword = null;
        try {
            oldpassword = us.makeDigest(req.getParameter("oldpassword"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String newpassword = null;
        try {
            newpassword = us.makeDigest(req.getParameter("newpassword"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = (User) req.getSession().getAttribute("current_user");
        resp.setContentType("text/html");
        if (user.getPassword().equals(oldpassword)) {
            try {
                us.updatePassword(newpassword, user.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                req.getSession().setAttribute("current_user", ud.getUserById(user.getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.getWriter().println(1);
        }
        else {
            resp.getWriter().println(0);
        }
    }
}
