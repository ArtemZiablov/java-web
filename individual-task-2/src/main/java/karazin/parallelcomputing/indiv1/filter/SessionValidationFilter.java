package karazin.parallelcomputing.indiv1.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@WebFilter("/*") // Apply filter to all URLs
public class SessionValidationFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(SessionValidationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Initializing SessionValidationFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String contextPath = httpRequest.getContextPath();
        String uri = httpRequest.getRequestURI();

        logger.debug("SessionValidationFilter: Checking session for URI {}", uri);

        // Exclude certain paths from session validation (e.g., static resources and login pages)
        if (isExcludedPath(uri, contextPath)) {
            logger.debug("SessionValidationFilter: Skipping session check for URI {}", uri);
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            // Session is active and user is authenticated
            logger.debug("SessionValidationFilter: Active session for user {}", session.getAttribute("username"));
            chain.doFilter(request, response);
        } else {
            // Session is inactive or user is not authenticated
            httpResponse.sendRedirect(contextPath + "/login.jsp?error=Please log in first.");
        }
    }

    private boolean isExcludedPath(String uri, String contextPath) {
        return uri.equals(contextPath + "/login") ||
                uri.equals(contextPath + "/register") ||
                uri.equals(contextPath + "/login.jsp") ||
                uri.equals(contextPath + "/registration.jsp") ||
                uri.startsWith(contextPath + "/css/") ||
                uri.startsWith(contextPath + "/js/") ||
                uri.startsWith(contextPath + "/images/") ||
                uri.startsWith(contextPath + "/fonts/");
    }

    @Override
    public void destroy() {
        logger.info("Destroying SessionValidationFilter");
    }
}
