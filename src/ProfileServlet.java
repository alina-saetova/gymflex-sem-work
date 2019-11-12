import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

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
        List<Integer> tr_ids = new ArrayList<>();
        try {
            tr_ids = std.getSavedTrainingsId(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Training> saved_trainings = new ArrayList<>();
        for (int id : tr_ids) {
            try {
                saved_trainings.add(td.getTrainingById(id));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        List<Integer> ex_ids = new ArrayList<>();
        try {
            ex_ids = sed.getSavedExercisesId(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Exercise> saved_exercises = new ArrayList<>();
        for (int id : ex_ids) {
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
        request.setAttribute("saved_trainings", saved_trainings);
        request.setAttribute("user", user);
        response.setContentType("text/html");
        RequestDispatcher rd = request.getRequestDispatcher("/profile_page");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}