import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteArticleServlet extends HttpServlet {

    UserExerciseDAO ued = new UserExerciseDAO();
    UserTrainingDAO utd = new UserTrainingDAO();

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
        String id =  req.getParameter("id");
        System.out.println(id);
        System.out.println(type);
        if (type.equals("exercise")) {
            try {
                st.executeUpdate("delete from fav_exercise_user where exercise_id = " + id + " AND user_id = " + user.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("own_training")) {
            System.out.println("qwe");
            try {
                utd.deleteUserTraining(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ued.deleteUserExercisesFromTraining(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {

            try {
                st.executeUpdate("delete from fav_training_user where training_id = " + id + " AND user_id = " + user.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
