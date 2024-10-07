package ua.karazin.javaweb.homework3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "cookieServlet", value = "/cookie-servlet")
public class CookieServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        // Отримуємо всі cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                out.println("<p>Cookie: " + cookie.getName() + " = " + cookie.getValue() + "</p>");
            }
        }

        // Створюємо новий cookie
        Cookie userCookie = new Cookie("user", "Анонімний користувач");
        userCookie.setMaxAge(60 * 60); // Встановлюємо час життя cookies на 1 годину
        response.addCookie(userCookie); // Додаємо cookie у відповідь

        out.println("<p>Новий cookie додано!</p>");
        out.println("</body></html>");
    }
}

