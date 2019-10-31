import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreatingTrainingPartTwoServlet extends HttpServlet {

    UserExerciseDAO ued = new UserExerciseDAO();
    UserTrainingDAO utd = new UserTrainingDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num = req.getParameter("ex_num");
        String name = req.getParameter("name");
        String tr_id = req.getParameter("tr_id");
        User user = (User) req.getSession().getAttribute("current_user");
        req.setAttribute("name", name);
        req.setAttribute("ex_num", num);
        req.setAttribute("tr_id", tr_id);
        resp.setContentType("text/html");
        RequestDispatcher rd = req.getRequestDispatcher("/create_training_two_page");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int i = Integer.parseInt(req.getParameter("ex_num"));
        String tr_id = req.getParameter("tr_id");
        System.out.println("i=" + i);
        for (int j = 1; j <= i; j++) {
            String ex_name = req.getParameter("" + j + "name");
            System.out.println(ex_name);
            String reps_num = req.getParameter("" + j + "reps");
            try {
                ued.createExercise(tr_id, ex_name, reps_num);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("/profile");
    }
}
