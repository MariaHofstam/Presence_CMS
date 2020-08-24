package se.lexicon.mariahofstam.Presence_CMS.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String text;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    private AttendanceStatus attendanceStatus;

    // Constructors
    public Note(){}

    public Note(int id, String title, String text, AttendanceStatus attendanceStatus) {
    	this(title, text, attendanceStatus);
    	this.id = id;
    }
    
    public Note(String title, String text, AttendanceStatus attendanceStatus) {
        this.title = title;
        this.text = text;
        this.attendanceStatus = attendanceStatus;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public AttendanceStatus getAttendanceStatus() {
    	return attendanceStatus;
    }


    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        Note note = (Note) o;
        return id == note.id &&
                Objects.equals(title, note.title) &&
                Objects.equals(text, note.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text);
    }
}
