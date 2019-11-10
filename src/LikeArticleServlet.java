import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LikeArticleServlet extends HttpServlet {

    SavedExerciseDAO sed = new SavedExerciseDAO();
    SavedTrainingDAO std = new SavedTrainingDAO();
    TrainingDAO td = new TrainingDAO();
    ExerciseDAO ed = new ExerciseDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        User user = (User) req.getSession().getAttribute("current_user");
        resp.setContentType("text/html");
        if (type.equals("exercise")) {
            String exercise_id = req.getParameter("exercise_id");
            try {
                sed.insert(exercise_id, user.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ed.updateLikes(exercise_id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                resp.getWriter().println(ed.getExerciseById(exercise_id).getCnt_likes());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            String training_id = req.getParameter("training_id");
            try {
                std.insert(training_id, user.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                td.updateLikes(training_id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                resp.getWriter().println(ed.getExerciseById(training_id).getCnt_likes());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
