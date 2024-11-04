package karazin.parallelcomputing.indiv1.servlet;

import karazin.parallelcomputing.indiv1.dao.EventDAO;
import karazin.parallelcomputing.indiv1.dao.EventDAOImpl;
import karazin.parallelcomputing.indiv1.model.Event;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Locale;


@WebServlet("/calendar")
public class CalendarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EventDAO eventDAO;

    @Override
    public void init() throws ServletException {
        eventDAO = new EventDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Check session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String username = (String) session.getAttribute("username");

        // Get the 'weekStart' parameter
        String weekStartParam = request.getParameter("weekStart");
        LocalDate weekStartDate;

        if (weekStartParam != null && !weekStartParam.trim().isEmpty()) {
            try {
                weekStartDate = LocalDate.parse(weekStartParam, DateTimeFormatter.ISO_DATE);
            } catch (Exception e) {
                // If parsing fails, use the current week's Monday
                weekStartDate = getMondayOfCurrentWeek(LocalDate.now());
            }
        } else {
            // Default to current week's Monday
            weekStartDate = getMondayOfCurrentWeek(LocalDate.now());
        }

        // Calculate week number
        Locale locale = Locale.getDefault(); // Adjust if necessary
        WeekFields weekFields = WeekFields.of(locale);
        int weekNumber = weekStartDate.get(weekFields.weekOfWeekBasedYear());
        int weekBasedYear = weekStartDate.get(weekFields.weekBasedYear());

        // Format dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentWeekStart = weekStartDate.format(formatter);
        String prevWeekStart = weekStartDate.minusWeeks(1).format(formatter);
        String nextWeekStart = weekStartDate.plusWeeks(1).format(formatter);

        // Get user's events
        List<Event> events = null;
        try {
            events = eventDAO.getEventsByUsername(username);
            System.out.println("Users events: " + events.size());
            for (Event event : events) {
                System.out.println(event);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Get week dates
        List<String> weekDates = getWeekDates(weekStartDate);

        // Pass attributes to JSP
        request.setAttribute("events", events);
        request.setAttribute("weekDates", weekDates);
        request.setAttribute("currentWeekStart", currentWeekStart);
        request.setAttribute("prevWeekStart", prevWeekStart);
        request.setAttribute("nextWeekStart", nextWeekStart);
        request.setAttribute("weekNumber", weekNumber);
        request.setAttribute("weekBasedYear", weekBasedYear);

        System.out.println("Forwarding to calendar.jsp with week number " + weekNumber + ", year " + weekBasedYear + " for user: " + username);
        request.getRequestDispatcher("/calendar.jsp").forward(request, response);
    }

    // Method to get Monday of the current week
    private LocalDate getMondayOfCurrentWeek(LocalDate date) {
        return date.with(DayOfWeek.MONDAY);
    }

    // Method to get list of dates for the week
    private List<String> getWeekDates(LocalDate weekStartDate) {
        List<String> dates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 7; i++) {
            LocalDate date = weekStartDate.plusDays(i);
            dates.add(date.format(formatter));
        }
        return dates;
    }

    // Additional methods for adding, updating, deleting events can be added as needed
}