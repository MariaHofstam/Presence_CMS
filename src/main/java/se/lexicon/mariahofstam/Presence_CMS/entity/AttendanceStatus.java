package se.lexicon.mariahofstam.Presence_CMS.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class AttendanceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private final LocalDateTime creationDateTime;

    // Constructor

    public AttendanceStatus(){
        creationDateTime = LocalDateTime.now();
    }

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
