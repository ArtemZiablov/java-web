package karazin.parallelcomputing.indiv1.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    // Використовуємо LocalDate для стовпця DATE
    @Column(name = "date", nullable = false)
    private LocalDate date;

    // Використовуємо LocalTime для стовпця TIME
    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "place", length = 255)
    private String place;

    @Column(name = "conference_link", length = 255)
    private String conferenceLink;

    /**
     * Відношення Many-to-One: кожна подія пов’язана з користувачем.
     * Зовнішній ключ у таблиці events – стовпець username, який посилається на username у таблиці users.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    // Конструктори
    public Event() {
    }

    public Event(String name, LocalDate date, LocalTime time, String description,
                 String place, String conferenceLink, User user) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        this.place = place;
        this.conferenceLink = conferenceLink;
        this.user = user;
    }

    public Event(int id, String name, LocalDate date, LocalTime time, String description,
                 String place, String conferenceLink, User user) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        this.place = place;
        this.conferenceLink = conferenceLink;
        this.user = user;
    }

    // Гетери та сеттери
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }

    public String getConferenceLink() {
        return conferenceLink;
    }
    public void setConferenceLink(String conferenceLink) {
        this.conferenceLink = conferenceLink;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "date = " + date + ", " +
                "time = " + time + ", " +
                "description = " + description + ", " +
                "place = " + place + ", " +
                "conferenceLink = " + conferenceLink + ", " +
                "user = " + user.getUsername() + ")";
    }
}
