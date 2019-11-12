package servlets;

import dao.ExerciseDAO;
import dao.SavedExerciseDAO;
import dao.SavedTrainingDAO;
import dao.TrainingDAO;
import models.User;
import services.ExerciseService;
import services.TrainingService;

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
    ExerciseService es = new ExerciseService();
    TrainingService ts = new TrainingService();

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
            int exercise_id = Integer.parseInt(req.getParameter("exercise_id"));
            try {
                sed.insert(exercise_id, user.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                es.updateLikes(exercise_id);
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
            int training_id = Integer.parseInt(req.getParameter("training_id"));
            try {
                std.insert(training_id, user.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ts.updateLikes(training_id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                resp.getWriter().println(td.getTrainingById(training_id).getCnt_likes());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
