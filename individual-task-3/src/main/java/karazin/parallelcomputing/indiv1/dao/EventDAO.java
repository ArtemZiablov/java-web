package karazin.parallelcomputing.indiv1.dao;

import karazin.parallelcomputing.indiv1.model.Event;

import java.util.List;

public interface EventDAO {
    void createEvent(Event event) throws Exception;
    Event getEventById(int eventId) throws Exception;
    List<Event> getEventsByUsername(String username) throws Exception;
    List<Event> getEventsByDate(String date) throws Exception;
    void updateEvent(Event event) throws Exception;
    void deleteEvent(int eventId) throws Exception;
}