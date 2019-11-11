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
    SavedArticlesService sas = new SavedArticlesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String type = req.getParameter("type");
        User user = (User) req.getSession().getAttribute("current_user");
        int id =  Integer.parseInt(req.getParameter("id"));
        if (type.equals("exercise") || type.equals("training")) {
            try {
                sas.deleteArticle(id, user.getId(), type);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
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
    }
}
