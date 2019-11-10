import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckAuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        User user = (User) req.getSession().getAttribute("current_user");
        System.out.println(req.getServletPath());
        if ((req.getServletPath().equals("/profile") || req.getServletPath().equals("/create_training_two")) && user == null) {
            System.out.println("l");
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
