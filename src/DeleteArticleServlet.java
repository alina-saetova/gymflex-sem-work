import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteArticleServlet extends HttpServlet {
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
                st.executeUpdate("delete from fav_exercise_user where exercise_id = " + exercise_id + " AND user_id = " + user.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            String training_id = req.getParameter("training_id");
            try {
                st.executeUpdate("delete from fav_training_user where training_id = " + training_id + " AND user_id = " + user.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
