package karazin.parallelcomputing.indiv1.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import karazin.parallelcomputing.indiv1.dao.EventDAO;
import karazin.parallelcomputing.indiv1.dao.EventDAOImpl;
import karazin.parallelcomputing.indiv1.model.Event;

import java.io.IOException;
import java.util.List;

@WebServlet("/eventDetails")
public class EventDetailsServlet extends HttpServlet {

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

        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect("/calendar");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("/calendar");
            return;
        }

        try {
            Event event = eventDAO.getEventById(id);
            if (event == null || !event.getUser().getUsername().equals(username)) {
                // Event not found or doesn't belong to the user
                response.sendRedirect("/calendar");
                return;
            }

            request.setAttribute("event", event);
            request.getRequestDispatcher("/eventDetails.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/calendar");
        }
    }
}
