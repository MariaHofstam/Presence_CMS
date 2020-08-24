package se.lexicon.mariahofstam.Presence_CMS.dtos;

import java.time.LocalDateTime;

public class AttendanceStatusDto {

	private int id;
	private LocalDateTime creationDateTime;
	private MemberDto member;
	private StatusCodeDto code;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public MemberDto getMember() {
		return member;
	}
	public void setMember(MemberDto member) {
		this.member = member;
	}
	
	public StatusCodeDto getCode() {
		return code;
	}
	public void setCode(StatusCodeDto code) {
		this.code = code;
	}
	
	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
}
