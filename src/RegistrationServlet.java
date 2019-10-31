import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;


@MultipartConfig
public class RegistrationServlet extends HttpServlet {

    UserDAO ud = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String name = request.getParameter("name");
        resp.setContentType("text/html");
        request.setAttribute("name", name);
        RequestDispatcher rd = request.getRequestDispatcher("/registration_page"); //jsp файл как отдельный сервлет
        rd.forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Statement statement = null;
        try {
            statement = ConnectionToDatabase.getConnection().createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        String login = req.getParameter("login");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String password = req.getParameter("password");
        Part p = req.getPart("photo");
        String localdir = "uploads";
        String pathDir = getServletContext().getRealPath("") + File.separator + localdir;
        File dir = new File(pathDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String[] filename_data = p.getSubmittedFileName().split("\\.");
        String filename = Math.random() + "." + filename_data[filename_data.length - 1];
        String fullpath = pathDir + File.separator + filename;
        p.write(fullpath);
        String photo = "/" + localdir + "/" + filename;
        try {
            statement.executeUpdate("insert into users (firstname, lastname, login, password, photo) VALUES ('" +
                    firstName +"', '" + lastName + "', '" + login + "', '" + password + "', '" + photo + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            req.getSession().setAttribute("current_user", ud.getUserByLogin(login));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/profile");

    }
}
