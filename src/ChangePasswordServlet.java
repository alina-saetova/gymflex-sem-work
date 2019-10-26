import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangePasswordServlet extends HttpServlet {

    UserDAO ud = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldpassword = req.getParameter("oldpassword");
        String newpassword = req.getParameter("newpassword");
        User user = (User) req.getSession().getAttribute("current_user");
        resp.setContentType("text/xml");
        if (user.getPassword().equals(oldpassword)) {
            Statement st = null;
            try {
                st = ConnectionToDatabase.getConnection().createStatement();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                st.executeUpdate("update users set password = '" + newpassword +
                        "' where id = " + user.getId());
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
