package ua.karazin.javaweb.homework7;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Проста перевірка імені користувача та пароля
        if ("admin".equals(username) && "password".equals(password)) {
            // Авторизація успішна, встановлюємо атрибут у сесії
            HttpSession session = request.getSession();
            session.setAttribute("user", username);

            // Перенаправляємо на захищену сторінку
            response.sendRedirect(request.getContextPath() + "/secured/welcome.jsp");
        } else {
            // Авторизація не вдалася, повертаємо на сторінку помилки
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
