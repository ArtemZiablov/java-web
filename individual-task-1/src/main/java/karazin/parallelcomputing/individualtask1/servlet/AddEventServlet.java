package karazin.parallelcomputing.individualtask1.servlet;

import karazin.parallelcomputing.individualtask1.model.Event;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/addEvent")
public class AddEventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Перевірка сесії
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String username = (String) session.getAttribute("username");

        // Отримання параметрів з форми
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String description = request.getParameter("description");
        String place = request.getParameter("place");
        String conferenceLink = request.getParameter("conferenceLink");

        // Валідація даних (можна додати додаткову перевірку)
        if (name == null || name.trim().isEmpty() ||
                date == null || date.trim().isEmpty() ||
                time == null || time.trim().isEmpty() ||
                description == null || description.trim().isEmpty()) {
            request.setAttribute("error", "Будь ласка, заповніть всі обов'язкові поля.");
            request.getRequestDispatcher("/addEvent.jsp").forward(request, response);
            return;
        }

        // Створення нового об'єкта Event з доданим username
        Event newEvent = new Event(name, date, time, description, place, conferenceLink, username);

        // Додавання події до списку користувача
        CalendarServlet.addEvent(username, newEvent);

        // Перенаправлення назад до календаря з поточним тижнем
        String weekStart = request.getParameter("weekStart");
        if (weekStart != null && !weekStart.trim().isEmpty()) {
            response.sendRedirect("calendar?weekStart=" + weekStart);
        } else {
            response.sendRedirect("calendar");
        }
    }
}
