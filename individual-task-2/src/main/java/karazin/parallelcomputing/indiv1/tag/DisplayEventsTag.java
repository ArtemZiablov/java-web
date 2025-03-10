package karazin.parallelcomputing.indiv1.tag;

import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import jakarta.servlet.jsp.JspException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import karazin.parallelcomputing.indiv1.dao.EventDAO;
import karazin.parallelcomputing.indiv1.dao.EventDAOImpl;
import karazin.parallelcomputing.indiv1.model.Event;

public class DisplayEventsTag extends SimpleTagSupport {
    private String date;
    private String username;  // Добавлено поле для имени пользователя

    public void setDate(String date) {
        this.date = date;
    }

    public void setUsername(String username) {  // Добавлен setter для имени пользователя
        this.username = username;
    }

    @Override
    public void doTag() throws JspException, IOException {
        // Получаем мероприятия из атрибута запроса
        List<Event> events = (List<Event>) getJspContext().findAttribute("events");

        if (events != null) {
            List<Event> eventsForDate = new ArrayList<>();

            // Фильтрация мероприятий по имени пользователя и дате
            for (Event event : events) {
                if (event.getDate().toString().equals(date) && event.getUser().getUsername().equals(username)) {
                    eventsForDate.add(event);
                }
            }

            if (!eventsForDate.isEmpty()) {
                for (Event event : eventsForDate) {
                    String eventLink = "/eventDetails?id=" + event.getId();
                    getJspContext().getOut().write(
                            "<li><a href='" + eventLink + "'>" + event.getName() + "</a></li>"
                    );
                }
            } else {
                getJspContext().getOut().write("<li>No events found.</li>");
            }
        } else {
            getJspContext().getOut().write("<li>No events found.</li>");
        }
    }
}

