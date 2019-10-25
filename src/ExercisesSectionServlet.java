import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExercisesSectionServlet extends HttpServlet {

    ExerciseDAO ed = new ExerciseDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Exercise> exercises = new ArrayList<>();
        try {
            exercises = ed.getAllExercises();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        req.setAttribute("allexercises", exercises);
        RequestDispatcher rd = req.getRequestDispatcher("/exercises_section_page");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Exercise> exercises = new ArrayList<>();
        try {
            exercises = ed.getExercisesByType(req.getParameter("muscle"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html");
        req.setAttribute("selected_exs", exercises);
        RequestDispatcher rd = req.getRequestDispatcher("/exercises_section_page");
        rd.forward(req, resp);
    }
}
