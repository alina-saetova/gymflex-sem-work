import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditProfileServlet extends HttpServlet {

    UserDAO ud = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        Statement st = null;
        try {
            st = ConnectionToDatabase.getConnection().createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //UPDATE films SET kind = 'Dramatic' WHERE kind = 'Drama';
        User user = (User) req.getSession().getAttribute("current_user");
        try {
            st.executeUpdate("update users set firstname = '" + firstname +
                    "', lastname = '" + lastname +
                    "', login = '" + login +
                    "' where id = " + user.getId());
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
