package karazin.parallelcomputing.indiv1.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import karazin.parallelcomputing.indiv1.dao.UserDAO;
import karazin.parallelcomputing.indiv1.dao.UserDAOImpl;
import karazin.parallelcomputing.indiv1.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;



@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Received login request");
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        logger.debug("Username: {}", username);
        logger.debug("Password: {}", password);

        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty()) {
            logger.warn("Validation failed: Missing username or password");
            request.setAttribute("error", "Please enter both username and password.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        try {
            User user = userDAO.getUserByUsername(username);
            if (user == null) {
                logger.warn("User {} not found", username);
                request.setAttribute("error", "Invalid username or password.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

            logger.debug("Stored password: {}", user.getPassword());

            if (user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                logger.info("User {} successfully logged in", username);
                response.sendRedirect("/calendar");
            } else {
                logger.warn("Invalid password for user {}", username);
                request.setAttribute("error", "Invalid username or password.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            logger.error("Error during login for user {}", username, e);
            request.setAttribute("error", "An error occurred during login. Please try again.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}