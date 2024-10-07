package ua.karazin.javaweb.homework3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "sessionServlet", value = "/session-servlet")
public class SessionServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Отримуємо HTTP-сесію
        HttpSession session = request.getSession();

        // Налаштування тривалості сесії (тривалість у секундах)
        session.setMaxInactiveInterval(600); // 10 хвилин

        // Отримуємо існуючий атрибут сесії або встановлюємо новий
        String userName = (String) session.getAttribute("userName");
        if (userName == null) {
            userName = "Анонімний користувач";
            session.setAttribute("userName", userName);
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Привіт, " + userName + "!</h1>");

        // Перевіряємо, чи хоче користувач завершити сесію
        if (request.getParameter("logout") != null) {
            session.invalidate(); // Завершуємо сесію
            out.println("<p>Сесія завершена.</p>");
        } else {
            out.println("<p>Тривалість сесії: " + session.getMaxInactiveInterval() + " секунд</p>");
        }

        out.println("</body></html>");
    }
}
