package karazin.parallelcomputing.individualtask1.servlet;

import karazin.parallelcomputing.individualtask1.model.Event;
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
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("/calendar")
public class CalendarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Мапа для зберігання подій кожного користувача
    private static Map<String, List<Event>> userEvents = new ConcurrentHashMap<>();

    @Override
    public void init() throws ServletException {
        // Ініціалізація подій за потребою
        // Наприклад, можна додати тестові події для певного користувача
        /*
        List<Event> eventsForUser = Collections.synchronizedList(new ArrayList<>());
        eventsForUser.add(new Event("Конференція Java", "2024-05-20", "10:00", "Офлайн конференція з Java технологій.", "Конференц-центр", "", "user1"));
        userEvents.put("user1", eventsForUser);
        */
        System.out.println("CalendarServlet initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Перевірка сесії
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String username = (String) session.getAttribute("username");

        // Отримання параметра weekStart (початок тижня)
        String weekStartParam = request.getParameter("weekStart");
        LocalDate weekStartDate;

        if (weekStartParam != null && !weekStartParam.trim().isEmpty()) {
            try {
                weekStartDate = LocalDate.parse(weekStartParam, DateTimeFormatter.ISO_DATE);
            } catch (Exception e) {
                // Якщо парсинг не вдався, використовуємо поточний тиждень
                weekStartDate = getMondayOfCurrentWeek(LocalDate.now());
            }
        } else {
            // Якщо параметра немає, використовуємо поточний тиждень
            weekStartDate = getMondayOfCurrentWeek(LocalDate.now());
        }

        // Форматування дати для відображення
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentWeekStart = weekStartDate.format(formatter);
        String prevWeekStart = weekStartDate.minusWeeks(1).format(formatter);
        String nextWeekStart = weekStartDate.plusWeeks(1).format(formatter);

        // Отримання списку подій для поточного користувача
        List<Event> events = userEvents.computeIfAbsent(username, k -> Collections.synchronizedList(new ArrayList<>()));

        // Обчислення дат обраного тижня (Понеділок - Неділя)
        List<String> weekDates = getWeekDates(weekStartDate);

        // Передача списку подій та дат тижня на JSP
        request.setAttribute("events", events);
        request.setAttribute("weekDates", weekDates);
        request.setAttribute("currentWeekStart", currentWeekStart);
        request.setAttribute("prevWeekStart", prevWeekStart);
        request.setAttribute("nextWeekStart", nextWeekStart);

        System.out.println("Forwarding to calendar.jsp with " + events.size() + " events and " + weekDates.size() + " week dates for user: " + username);
        request.getRequestDispatcher("/calendar.jsp").forward(request, response);
    }

    // Метод для отримання початку поточного тижня (понеділок)
    private LocalDate getMondayOfCurrentWeek(LocalDate date) {
        return date.with(DayOfWeek.MONDAY);
    }

    // Метод для отримання списку дат тижня
    private List<String> getWeekDates(LocalDate weekStartDate) {
        List<String> dates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 7; i++) {
            LocalDate date = weekStartDate.plusDays(i);
            dates.add(date.format(formatter));
        }
        return dates;
    }

    // Метод для додавання події конкретному користувачу
    public static void addEvent(String username, Event event) {
        List<Event> events = userEvents.computeIfAbsent(username, k -> Collections.synchronizedList(new ArrayList<>()));
        events.add(event);
    }

    // Метод для отримання подій конкретного користувача
    public static List<Event> getEvents(String username) {
        return userEvents.get(username);
    }
}
