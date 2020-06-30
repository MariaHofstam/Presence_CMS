package se.lexicon.mariahofstam.Presence_CMS.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class AttendanceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private final LocalDateTime creationDateTime;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    private Member member;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    private StatusCode code;


    // Constructor

    public AttendanceStatus(){
        creationDateTime = LocalDateTime.now();
    }

    public AttendanceStatus(LocalDateTime creationDateTime, Member member, StatusCode code) {
        this.creationDateTime = creationDateTime;
        this.member = member;
        this.code = code;
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
