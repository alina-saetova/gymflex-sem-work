package servlets;

import dao.CommentaryDAO;
import dao.TrainingDAO;
import models.Commentary;
import models.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class CommentaryServlet extends HttpServlet {

    TrainingDAO td = new TrainingDAO();
    CommentaryDAO cd = new CommentaryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        int article_id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        int user_id = user.getId();
        Date date = new Date();
        String content = request.getParameter("text");
        String type = request.getParameter("type");
        int id = 0;
        try {
            id = cd.insert(user_id, article_id, date, content, type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Commentary commentary = null;
        try {
            commentary = new Commentary(id, user_id, article_id, date, content, type);
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
        JSONArray ja = new JSONArray();
        ja.put(new JSONObject(commentary));
        JSONObject jo = new JSONObject();
        jo.put("objects", ja);
        resp.setContentType("text/json");
        resp.getWriter().write(jo.toString());
    }
}
