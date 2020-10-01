package se.lexicon.mariahofstam.Presence_CMS.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean active;
    private String firstName;
    private String lastName;
    private String phone;
    private String eMail;
    private String extraInfo;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    private Organisation group;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY,
            mappedBy = "member",     //declared in class AttendenceStatus, a @ManyToOne
            orphanRemoval = true
    )
    private List<AttendanceStatus> attendanceList;    //List of attendance-statuses for this member



    // Constructors
    public Member(){}

    public Member(int id, boolean active, String firstName, String lastName, String phone, String eMail, String extraInfo, Organisation group) {
        this(active, firstName, lastName, phone, eMail, extraInfo, group);
        this.id = id;
    }

    public Member(boolean active, String firstName, String lastName, String phone, String eMail, String extraInfo, Organisation group) {
        this.active = active;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.eMail = eMail;
        this.extraInfo = extraInfo;
        this.group = group;
        this.attendanceList = new ArrayList<>();
    }


    //Methods for adding and removing attendance-statuses for the member to the attendanceList
    public void addAttendanceStatus(StatusCode code) {
        AttendanceStatus attendance = new AttendanceStatus(LocalDateTime.now(), this, code);

        if (!attendanceList.contains(attendance)) {
            attendanceList.add(attendance);
        }

    }

    public void removeAttendanceStatus(AttendanceStatus attendance) {
        if(attendanceList.contains(attendance)) {
            attendanceList.remove(attendance);
        }
    }



    //Getters and Setters
    public int getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Organisation getGroup() {
        return group;
    }


    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", active=" + active +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", eMail='" + eMail + '\'' +
                ", extraInfo='" + extraInfo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return id == member.id &&
                active == member.active &&
                Objects.equals(firstName, member.firstName) &&
                Objects.equals(lastName, member.lastName) &&
                Objects.equals(phone, member.phone) &&
                Objects.equals(eMail, member.eMail) &&
                Objects.equals(extraInfo, member.extraInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, active, firstName, lastName, phone, eMail, extraInfo);
    }

}
