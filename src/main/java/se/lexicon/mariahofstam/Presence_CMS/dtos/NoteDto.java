package se.lexicon.mariahofstam.Presence_CMS.dtos;

public class NoteDto {

	private int id;
	private String title;
	private String text;
	private AttendanceStatusDto attendanceStatus;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public AttendanceStatusDto getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setAttendanceStatus(AttendanceStatusDto attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
}
