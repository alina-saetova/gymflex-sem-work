import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseServlet extends HttpServlet {

    ExerciseDAO ed = new ExerciseDAO();
    CommentaryDAO cd = new CommentaryDAO();
    SavedExerciseDAO sed = new SavedExerciseDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(request, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Exercise e = null;
        try {
            e = ed.getExerciseById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        List<Commentary> comms = new ArrayList<>();
        try {
            comms = cd.getArticleCommentaries(id, "exercise");
        } catch (SQLException | ParseException ex) {
            ex.printStackTrace();
        }
        User user = (User) req.getSession().getAttribute("current_user");
        String flag = "true";
        if (user != null) {
            try {
                flag = sed.checkLike(user.getId(), id);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        req.setAttribute("user", user);
        req.setAttribute("flag", flag);
        req.setAttribute("comms", comms);
        req.setAttribute("exercise", e);
        resp.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("/exercise_page");
        rd.forward(req, resp);
    }


}
