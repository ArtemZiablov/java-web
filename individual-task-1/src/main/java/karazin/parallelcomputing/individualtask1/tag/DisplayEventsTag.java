package karazin.parallelcomputing.individualtask1.tag;

import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import jakarta.servlet.jsp.JspException;
import java.io.IOException;
import java.util.List;
import karazin.parallelcomputing.individualtask1.model.Event;

public class DisplayEventsTag extends SimpleTagSupport {
    private String date;

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public void doTag() throws JspException, IOException {
        // Отримання списку подій з контексту
        List<Event> events = (List<Event>) getJspContext().findAttribute("events");

        if (events != null) {
            System.out.println("Displaying events for date: " + date);
            synchronized (events) { // Синхронізація для безпеки
                for (int i = 0; i < events.size(); i++) {
                    Event event = events.get(i);
                    if (event.getDate().equals(date)) {
                        System.out.println("Found event: " + event.getName());
                        // Відображення події
                        getJspContext().getOut().write(
                                "<li><a href='eventDetails?id=" + i + "'>" +
                                        event.getName() +
                                        "</a></li>"
                        );
                    }
                }
            }
        } else {
            System.out.println("Events attribute is null.");
            getJspContext().getOut().write("<li>Події не знайдені.</li>");
        }
    }
}
