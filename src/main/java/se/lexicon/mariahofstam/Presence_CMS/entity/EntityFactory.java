package se.lexicon.mariahofstam.Presence_CMS.entity;

import java.time.LocalDateTime;

public abstract class EntityFactory {

    protected AttendanceStatus createAttendanceStatus(int id, LocalDateTime creationDateTime, Member member, StatusCode code) {
        return new AttendanceStatus(id, creationDateTime, member, code);
    }

    protected Note createNote(int id, String title, String text, AttendanceStatus attendanceStatus) {
        return new Note(id, title, text, attendanceStatus);
    }

    protected Member createMember(int id, boolean active, String firstName, String lastName, String phone, String eMail, String extraInfo, Organisation group) {
        return new Member(id, active, firstName, lastName, phone, eMail, extraInfo, group);
    }

    protected Organisation createOrganisation(int id, String groupName) {
        return new Organisation(id, groupName);
    }

    protected StatusCode createStatusCode(int id, String codeName, String shortDescription, ColorCode colorCode) {
        return new StatusCode(id, codeName, shortDescription, colorCode);
    }
}
