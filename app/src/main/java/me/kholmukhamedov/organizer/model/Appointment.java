package me.kholmukhamedov.organizer.model;

import java.util.Date;
import java.util.SortedSet;

public class Appointment {
    private Date start, end;
    private String title, description;
    private SortedSet <Date> notifications;

    public Appointment(Date start, Date end, String title, String description, SortedSet<Date> notifications) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.description = description;
        this.notifications = notifications;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SortedSet<Date> getNotifications() {
        return notifications;
    }

    public void setNotifications(SortedSet<Date> notifications) {
        this.notifications = notifications;
    }
}
