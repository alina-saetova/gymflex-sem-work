import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class LikeArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        Statement st = null;
        try {
            st = ConnectionToDatabase.getConnection().createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        User user = (User) req.getSession().getAttribute("current_user");
        if (type.equals("exercise")) {
            String exercise_id = req.getParameter("exercise_id");
            try {
                st.executeUpdate("insert into fav_exercise_user values (" + exercise_id + ", " + user.getId() + ");");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            String training_id = req.getParameter("training_id");
            try {
                st.executeUpdate("insert into fav_training_user values (" + training_id + ", " + user.getId() + ");");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
