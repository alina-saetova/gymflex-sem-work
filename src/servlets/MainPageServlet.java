package servlets;

import models.Exercise;
import models.Training;
import services.ExerciseService;
import services.TrainingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MainPageServlet extends HttpServlet {

    TrainingService ts = new TrainingService();
    ExerciseService es = new ExerciseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Exercise> exercises = null;
        List<Training> trainings = null;
        try {
            exercises = es.getLastAddedExercises();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            trainings = ts.getLastAddedTrainings();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("exercises", exercises);
        req.setAttribute("trainings", trainings);
        resp.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("/main_page");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
