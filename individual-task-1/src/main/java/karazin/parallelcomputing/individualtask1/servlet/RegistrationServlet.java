package karazin.parallelcomputing.individualtask1.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import karazin.parallelcomputing.individualtask1.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    // Статичний список користувачів
    private static List<User> users = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Перевірка, чи існує користувач
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                request.setAttribute("error", "Користувач вже існує.");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            }
        }

        // Додавання нового користувача
        User newUser = new User(username, password);
        users.add(newUser);

        // Перенаправлення на сторінку авторизації
        response.sendRedirect("login.jsp");
    }

    // Метод для отримання списку користувачів (може використовуватись у LoginServlet)
    public static List<User> getUsers() {
        return users;
    }
}