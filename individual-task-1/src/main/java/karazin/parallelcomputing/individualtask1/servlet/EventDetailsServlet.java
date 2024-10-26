package karazin.parallelcomputing.individualtask1.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import karazin.parallelcomputing.individualtask1.model.Event;

import java.io.IOException;
import java.util.List;

@WebServlet("/eventDetails")
public class EventDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Перевірка сесії
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String username = (String) session.getAttribute("username");

        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect("calendar");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("calendar");
            return;
        }

        // Отримання списку подій користувача
        List<Event> events = CalendarServlet.getEvents(username);
        if (events == null || id < 0 || id >= events.size()) {
            response.sendRedirect("calendar");
            return;
        }

        Event event = events.get(id);
        request.setAttribute("event", event);
        request.getRequestDispatcher("/eventDetails.jsp").forward(request, response);
    }
}
