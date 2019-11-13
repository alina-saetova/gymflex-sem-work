package servlets;

import dao.*;
import models.*;
import services.ExerciseService;
import services.SavedArticlesService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TrainingServlet extends HttpServlet {

    TrainingDAO td = new TrainingDAO();
    CommentaryDAO cd = new CommentaryDAO();
    SavedArticlesService sas = new SavedArticlesService();
    ExerciseDAO ed = new ExerciseDAO();
    ExerciseService es = new ExerciseService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Training t = null;
        try {
            t = td.getTrainingById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        List<Commentary> comms = new ArrayList<>();
        try {
            comms = cd.getAll(id, "training");
        } catch (SQLException | ParseException ex) {
            ex.printStackTrace();
        }

        List<Exercise> exercises = new ArrayList<>();
        try {
            exercises = es.getExercisesFromTraining(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User user = (User) req.getSession().getAttribute("current_user");
        String flag = "true";
        if (user != null) {
            try {
                flag = sas.checkLike(user.getId(), id, "training");
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
