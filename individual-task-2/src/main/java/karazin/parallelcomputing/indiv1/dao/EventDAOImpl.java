package karazin.parallelcomputing.indiv1.dao;


import karazin.parallelcomputing.indiv1.model.Event;
import karazin.parallelcomputing.indiv1.util.DatabaseUtil;

import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventDAOImpl implements EventDAO {

    @Override
    public void createEvent(Event event) throws Exception {
        String sql = "INSERT INTO events (name, date, time, description, place, conference_link, username) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getName());
            stmt.setDate(2, java.sql.Date.valueOf(event.getDate()));

            // Форматирование времени
            String time = event.getTime();
            LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm")); // Если time="14:30"
            stmt.setTime(3, java.sql.Time.valueOf(localTime));

            stmt.setString(4, event.getDescription());
            stmt.setString(5, event.getPlace());
            stmt.setString(6, event.getConferenceLink());
            stmt.setString(7, event.getUsername());

            stmt.executeUpdate();
        }
    }


    @Override
    public Event getEventById(int eventId) throws Exception {
        String sql = "SELECT * FROM events WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, eventId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Event event = new Event();
                    event.setId(rs.getInt("id"));
                    event.setName(rs.getString("name"));
                    event.setDate(rs.getDate("date").toString());
                    event.setTime(rs.getTime("time").toString());
                    event.setDescription(rs.getString("description"));
                    event.setPlace(rs.getString("place"));
                    event.setConferenceLink(rs.getString("conference_link"));
                    event.setUsername(rs.getString("username"));
                    return event;
                }
            }
        }
        return null;
    }

    @Override
    public List<Event> getEventsByUsername(String username) throws Exception {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events WHERE username = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Event event = new Event();
                    event.setId(rs.getInt("id"));
                    event.setName(rs.getString("name"));
                    event.setDate(rs.getDate("date").toString());
                    event.setTime(rs.getTime("time").toString());
                    event.setDescription(rs.getString("description"));
                    event.setPlace(rs.getString("place"));
                    event.setConferenceLink(rs.getString("conference_link"));
                    event.setUsername(rs.getString("username"));
                    events.add(event);
                }
            }
        }
        return events;
    }


    @Override
    public List<Event> getEventsByDate(String date) throws Exception {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events WHERE date = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(date));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Event event = new Event();
                    event.setId(rs.getInt("id"));
                    event.setName(rs.getString("name"));
                    event.setDate(rs.getDate("date").toString());
                    event.setTime(rs.getTime("time").toString());
                    event.setDescription(rs.getString("description"));
                    event.setPlace(rs.getString("place"));
                    event.setConferenceLink(rs.getString("conference_link"));
                    event.setUsername(rs.getString("username"));
                    events.add(event);
                }
            }
        }
        return events;
    }

    @Override
    public void updateEvent(Event event) throws Exception {
        String sql = "UPDATE events SET name = ?, date = ?, time = ?, description = ?, place = ?, conference_link = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, event.getName());
            stmt.setDate(2, java.sql.Date.valueOf(event.getDate()));
            stmt.setTime(3, java.sql.Time.valueOf(event.getTime()));
            stmt.setString(4, event.getDescription());
            stmt.setString(5, event.getPlace());
            stmt.setString(6, event.getConferenceLink());
            stmt.setInt(7, event.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteEvent(int eventId) throws Exception {
        String sql = "DELETE FROM events WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, eventId);
            stmt.executeUpdate();
        }
    }
}