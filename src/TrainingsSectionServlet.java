import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainingsSectionServlet extends HttpServlet {

    TrainingDAO td = new TrainingDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Training> trainings = new ArrayList<>();
        try {
            trainings = td.getAllTrainings();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        req.setAttribute("alltrainings", trainings);
        RequestDispatcher rd = req.getRequestDispatcher("/trainings_section_page");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Training> trainings = new ArrayList<>();
        try {
            trainings = td.getTrainingsByType(req.getParameter("gender"), req.getParameter("purpose"), req.getParameter("location"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONArray ja = new JSONArray();
        for (Training training: trainings) {
            ja.put(new JSONObject(training));
        }
        JSONObject jo = new JSONObject();
        jo.put("objects", ja);
        resp.setContentType("text/json");
        resp.getWriter().write(jo.toString());
    }
}
