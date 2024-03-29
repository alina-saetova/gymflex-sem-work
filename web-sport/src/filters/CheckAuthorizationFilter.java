package filters;

import dao.UserDAO;
import models.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CheckAuthorizationFilter implements Filter {

    UserDAO ud = new UserDAO();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        User user = (User) req.getSession().getAttribute("current_user");
        Cookie[] cookies = req.getCookies();
        if (user == null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("user_login")) {
                    try {
                        user = ud.getUserByLogin(c.getValue());
                        req.getSession().setAttribute("current_user", user);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if ((req.getServletPath().equals("/profile") || req.getServletPath().equals("/create_training_two")) && user == null) {
            resp.sendRedirect("/login");
        }
        else if ((req.getServletPath().equals("/registration") || req.getServletPath().equals("/login")) && user != null) {
            resp.sendRedirect("/profile");
        }
        else  {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
