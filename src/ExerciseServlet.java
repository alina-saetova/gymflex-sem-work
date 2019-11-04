import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
        String id = req.getParameter("id");
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
        String user_id = null;
        if (user != null) {
            user_id = user.getId();
        }
        String flag = "";
        try {
            flag = sed.checkLike(user_id, id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        req.setAttribute("flag", flag);
        req.setAttribute("comms", comms);
        req.setAttribute("exercise", e);
        resp.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("/exercise_page");
        rd.forward(req, resp);
    }


}
