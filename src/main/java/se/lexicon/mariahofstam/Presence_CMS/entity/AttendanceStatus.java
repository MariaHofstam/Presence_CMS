package se.lexicon.mariahofstam.Presence_CMS.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class AttendanceStatus {
    private int id;
    private final LocalDateTime creationDateTime;

    // Constructor
    public AttendanceStatus(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }


    @Override
    public String toString() {
        return "AttendanceStatus{" +
                "id=" + id +
                ", creationDateTime=" + creationDateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttendanceStatus)) return false;
        AttendanceStatus that = (AttendanceStatus) o;
        return id == that.id &&
                Objects.equals(creationDateTime, that.creationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDateTime);
    }
}
