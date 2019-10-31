import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileServlet extends HttpServlet {

    UserDAO ud = new UserDAO();
    TrainingDAO td = new TrainingDAO();
    SavedTrainingDAO std = new SavedTrainingDAO();
    SavedExerciseDAO sed = new SavedExerciseDAO();
    ExerciseDAO ed = new ExerciseDAO();
    UserTrainingDAO utd = new UserTrainingDAO();
    UserExerciseDAO ued = new UserExerciseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("current_user");
        if (user == null) {
            response.sendRedirect("/login");
        }
        else {
            List<String> tr_ids = new ArrayList<>();
            try {
                tr_ids = std.getSavedTrainingsId(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<Training> saved_trainings = new ArrayList<>();
            for (String id : tr_ids) {
                try {
                    saved_trainings.add(td.getTrainingById(id));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            request.setAttribute("saved_trainings", saved_trainings);

            List<String> ex_ids = new ArrayList<>();
            try {
                ex_ids = sed.getSavedExercisesId(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<Exercise> saved_exercises = new ArrayList<>();
            for (String id : ex_ids) {
                try {
                    saved_exercises.add(ed.getExerciseById(id));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            Map<UserTraining, List<UserExercise>> map = new HashMap<>();
            List<UserTraining> ut = new ArrayList<>();
            try {
                ut = utd.getUserTrainings(user.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (UserTraining u : ut) {
                try {
                    map.put(u, ued.getExercisesFromUserTraining(u.getId()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            request.setAttribute("map", map);
            request.setAttribute("saved_exercises", saved_exercises);
            request.setAttribute("user", user);
            response.setContentType("text/html");
            RequestDispatcher rd = request.getRequestDispatcher("/profile_page");
            rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
