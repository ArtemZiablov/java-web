package karazin.parallelcomputing.indiv1.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import karazin.parallelcomputing.indiv1.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebFilter("/*")
public class DatabaseConnectionFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Initialization of DatabaseConnectionFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        logger.debug("DatabaseConnectionFilter: Check connection for URI {}", uri);

        // Определяем, требует ли URL соединения с БД
        boolean requiresDB = requiresDatabaseConnection(uri, contextPath);

        if (requiresDB) {
            try (Connection connection = DatabaseUtil.getConnection()) {
                if (connection == null || connection.isClosed()) {
                    logger.error("DatabaseConnectionFilter: Unable to establish DB connection for URI {}", uri);
                    throw new ServletException("Невозможно установить соединение с базой данных.");
                }

                // Устанавливаем соединение в атрибут запроса
                request.setAttribute("connection", connection);
                logger.debug("DatabaseConnectionFilter: DB connection established for URI {}", uri);

                // Продолжаем цепочку фильтров
                chain.doFilter(request, response);
            } catch (SQLException e) {
                logger.error("DatabaseConnectionFilter: Error connecting to DB", e);
                throw new ServletException("Ошибка при подключении к базе данных.", e);
            }
        } else {
            // Если соединение не требуется, продолжаем цепочку фильтров
            chain.doFilter(request, response);
        }
    }

    private boolean requiresDatabaseConnection(String uri, String contextPath) {
        // Определите, для каких URL требуется соединение с БД
        // Например, для /calendar/* и других функциональностей
        return uri.startsWith(contextPath + "/calendar/") || uri.startsWith(contextPath + "/eventDetails/");
    }

    @Override
    public void destroy() {
        logger.info("DatabaseConnectionFilter shutdown");
    }
}
