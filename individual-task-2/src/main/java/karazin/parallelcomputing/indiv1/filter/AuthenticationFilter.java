package karazin.parallelcomputing.indiv1.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Initialization of AuthFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String contextPath = req.getContextPath();
        String uri = req.getRequestURI();
        logger.debug("AuthFilter: Checking access to URI {}", uri);

        // Определение, требует ли URL авторизации
        boolean isProtectedPath = uri.startsWith(contextPath + "/calendar/");

        if (isProtectedPath) {
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("username") == null) {
                logger.warn("AuthFilter: Unauthorized access to {}", uri);
                res.sendRedirect(contextPath + "/login.jsp?error=Please log in first.");
                return;
            } else {
                logger.debug("AuthFilter: User {} is logged in", session.getAttribute("username"));
            }
        }

        // Продолжаем цепочку фильтров
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("Shutting down AuthFilter");
    }
}
