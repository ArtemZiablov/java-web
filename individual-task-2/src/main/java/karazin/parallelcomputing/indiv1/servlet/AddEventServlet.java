package karazin.parallelcomputing.indiv1.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import karazin.parallelcomputing.indiv1.dao.EventDAO;
import karazin.parallelcomputing.indiv1.dao.EventDAOImpl;
import karazin.parallelcomputing.indiv1.dao.UserDAO;
import karazin.parallelcomputing.indiv1.dao.UserDAOImpl;
import karazin.parallelcomputing.indiv1.model.Event;
import karazin.parallelcomputing.indiv1.model.User;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@WebServlet("/addEvent")
public class AddEventServlet extends HttpServlet {

    private EventDAO eventDAO;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        eventDAO = new EventDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Проверка сессии
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String username = (String) session.getAttribute("username");

        // Получение параметров
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String description = request.getParameter("description");
        String place = request.getParameter("place");
        String conferenceLink = request.getParameter("conferenceLink");
        String weekStart = request.getParameter("weekStart");

        // Базовая валидация
        if (name == null || name.trim().isEmpty() ||
                date == null || date.trim().isEmpty() ||
                time == null || time.trim().isEmpty() ||
                description == null || description.trim().isEmpty()) {
            request.setAttribute("error", "Please fill in all required fields.");
            request.getRequestDispatcher("/addEvent.jsp").forward(request, response);
            return;
        }

        try {
            // Обработка даты и времени
            LocalDate eventDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            LocalTime eventTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));

            // Создание события
            User user = userDAO.getUserByUsername(username);
            Event event = new Event(name, eventDate, eventTime, description, place, conferenceLink, user);
            eventDAO.createEvent(event);

            // Перенаправление обратно на календарь с текущей неделей
            if (weekStart != null && !weekStart.trim().isEmpty()) {
                response.sendRedirect("calendar?weekStart=" + weekStart);
            } else {
                response.sendRedirect("calendar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while adding the event. Please try again.");
            request.getRequestDispatcher("/addEvent.jsp").forward(request, response);
        }
    }

}
