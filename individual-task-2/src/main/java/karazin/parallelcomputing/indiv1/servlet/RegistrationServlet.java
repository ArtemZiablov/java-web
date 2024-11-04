package karazin.parallelcomputing.indiv1.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import karazin.parallelcomputing.indiv1.dao.UserDAO;
import karazin.parallelcomputing.indiv1.dao.UserDAOImpl;
import karazin.parallelcomputing.indiv1.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Basic validation
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                confirmPassword == null || confirmPassword.isEmpty()) {
            request.setAttribute("error", "Please fill in all required fields.");
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match.");
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
            return;
        }

        try {
            // Check if user already exists
            if (userDAO.getUserByUsername(username) != null) {
                request.setAttribute("error", "Username already exists.");
                request.getRequestDispatcher("/registration.jsp").forward(request, response);
                return;
            }

            // Create user
            User user = new User(username, password);
            userDAO.createUser(user);

            // Redirect to login page
            response.sendRedirect("login.jsp?message=Registration successful. Please log in.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred during registration. Please try again.");
            request.getRequestDispatcher("/registration.jsp").forward(request, response);
        }
    }
}