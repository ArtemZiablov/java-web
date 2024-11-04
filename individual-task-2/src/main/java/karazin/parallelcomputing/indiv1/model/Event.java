package karazin.parallelcomputing.indiv1.model;


public class Event {
    private int id;
    private String name;
    private String date;
    private String time;
    private String description;
    private String place;
    private String conferenceLink;
    private String username; // To associate event with a user

    // Constructors
    public Event() {}

    public Event(String name, String date, String time, String description, String place, String conferenceLink, String username) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        this.place = place;
        this.conferenceLink = conferenceLink;
        this.username = username;
    }

    public Event(int id, String name, String date, String time, String description, String place, String conferenceLink, String username) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        this.place = place;
        this.conferenceLink = conferenceLink;
        this.username = username;
    }

    // Getters and Setters
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}