package ua.karazin.javaweb.homework7;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Удаляємо сесію та всі атрибути
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();  // Закриваємо сесію
        }

        // Перенаправляємо на сторінку для входу (login.jsp)
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}