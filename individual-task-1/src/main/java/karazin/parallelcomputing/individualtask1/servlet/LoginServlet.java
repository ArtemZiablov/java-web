package karazin.parallelcomputing.individualtask1.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import karazin.parallelcomputing.individualtask1.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        List<User> users = RegistrationServlet.getUsers();
        boolean found = false;

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                found = true;
                break;
            }
        }

        if (found) {
            // Створення сесії
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            // Перенаправлення на сервлет календаря
            response.sendRedirect("calendar");
        } else {
            request.setAttribute("error", "Невірний логін або пароль.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
