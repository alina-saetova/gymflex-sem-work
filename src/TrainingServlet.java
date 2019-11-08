import freemarker.core.JSONOutputFormat;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainingServlet extends HttpServlet {

    TrainingDAO td = new TrainingDAO();
    CommentaryDAO cd = new CommentaryDAO();
    SavedTrainingDAO std = new SavedTrainingDAO();
    ExerciseDAO ed = new ExerciseDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Training t = null;
        try {
            t = td.getTrainingById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        List<Commentary> comms = new ArrayList<>();
        try {
            comms = cd.getArticleCommentaries(id, "training");
        } catch (SQLException | ParseException ex) {
            ex.printStackTrace();
        }

        List<Exercise> exercises = new ArrayList<>();
        try {
            exercises = ed.getExercisesFromTraining(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User user = (User) req.getSession().getAttribute("current_user");
        String flag = "";
        if (user != null) {
            try {
                flag = std.checkLike(user.getId(), id);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        req.setAttribute("user", user);
        req.setAttribute("flag", flag);
        req.setAttribute("comms", comms);
        req.setAttribute("exercises", exercises);
        req.setAttribute("training", t);
        resp.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("/training_page");
        rd.forward(req, resp);
    }


}
