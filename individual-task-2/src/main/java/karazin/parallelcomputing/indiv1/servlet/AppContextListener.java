package karazin.parallelcomputing.indiv1.servlet;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import karazin.parallelcomputing.indiv1.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class AppContextListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(AppContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Application is starting up.");
        // Add any initialization logic here
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Application is shutting down.");
        // Add any cleanup logic here
    }

}
