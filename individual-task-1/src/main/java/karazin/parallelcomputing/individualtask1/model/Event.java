package karazin.parallelcomputing.individualtask1.model;

public class Event {
    private String name;
    private String date;
    private String time;
    private String description;
    private String place;
    private String conferenceLink;
    private String username; // Нове поле

    // Конструктор включаючи username
    public Event(String name, String date, String time, String description, String place, String conferenceLink, String username) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        this.place = place;
        this.conferenceLink = conferenceLink;
        this.username = username;
    }

    // Геттери та сеттери
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace() {
        return place;
    }

    public String getConferenceLink() {
        return conferenceLink;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Інші геттери та сеттери за потребою
}
