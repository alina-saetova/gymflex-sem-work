import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TreeMap;

public class CalculatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher rd = request.getRequestDispatcher("/calculator_page");
        rd.forward(request, response);    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");
        String weight = request.getParameter("weight");
        String height = request.getParameter("height");
        String activity = request.getParameter("activity");
        String formula = request.getParameter("formula");
        TreeMap<String, Double> act = new TreeMap<>();
        act.put("none", 1.2);
        act.put("small", 1.375);
        act.put("medium", 1.55);
        act.put("big", 1.725);
        act.put("huge", 1.9);
        long result;
        if (gender.equals("female")) {
            if (formula.equals("harris")) {
                result = Math.round((447.593 + 9.247 * Double.parseDouble(weight)
                        + 3.098 * Integer.parseInt(height)
                        - 4.33 * Integer.parseInt(age))
                        * act.get(activity));
            }
            else {
                result = Math.round((10 * Double.parseDouble(weight)
                        + 6.25 * Integer.parseInt(height)
                        - 5 * Integer.parseInt(age)
                        - 161)
                        * act.get(activity));
            }
        }
        else {
            if (formula.equals("harris")) {
                result = Math.round((float)(88.362 + 13.397 * Double.parseDouble(weight)
                        + 4.799 * Integer.parseInt(height)
                        - 5.677 * Integer.parseInt(age))
                        * act.get(activity));
            }
            else {
                result = Math.round((10 * Double.parseDouble(weight)
                        + 6.25 * Integer.parseInt(height)
                        - 5 * Integer.parseInt(age)
                        + 5)
                        * act.get(activity));
            }
        }
        response.getWriter().println(result);
    }
}
