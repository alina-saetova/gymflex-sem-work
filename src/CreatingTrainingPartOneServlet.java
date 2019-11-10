import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreatingTrainingPartOneServlet extends HttpServlet {

    UserTrainingDAO utd = new UserTrainingDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("current_user");
        boolean flag = false;
        if (user != null) {
            flag = true;
        }
        req.setAttribute("flag", flag);
        resp.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("/create_training_one_page");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num = req.getParameter("exercises_number");
        String name = req.getParameter("training_name");
        User user = (User) req.getSession().getAttribute("current_user");
        int tr_id = 0;
        try {
            tr_id = utd.createTraining(user.getId(), name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        resp.sendRedirect("/create_training_two?name=" + name + "&ex_num=" + num + "&tr_id=" + tr_id);

    }
}
