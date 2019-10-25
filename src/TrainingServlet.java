import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainingServlet extends HttpServlet {

    TrainingDAO td = new TrainingDAO();
    CommentaryDAO cd = new CommentaryDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        Statement statement = null;
        try {
            statement = ConnectionToDatabase.getConnection().createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        String article_id = request.getParameter("id");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        String user_id = user.getId();
        Date date = new Date();
        String content = request.getParameter("text");
        System.out.println(content);
        try {
            statement.executeUpdate("insert into commentaries (user_id, article_id, date, content) VALUES ('" +
                    user_id +"', '" + article_id + "', '" + date + "', '" + content + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/training?id=" + article_id);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Training t = null;
        try {
            t = td.getTrainingById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        List<Commentary> comms = new ArrayList<>();
        try {
            comms = cd.getArticleCommentaries(id);
        } catch (SQLException | ParseException ex) {
            ex.printStackTrace();
        }
        req.setAttribute("comms", comms);
        req.setAttribute("training", t);
        resp.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("/training_page");
        rd.forward(req, resp);
    }
}
