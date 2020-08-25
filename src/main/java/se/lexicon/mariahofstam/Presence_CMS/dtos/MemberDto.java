package se.lexicon.mariahofstam.Presence_CMS.dtos;

public class MemberDto {

	private int id;
	private boolean active;
	private String firstName;
	private String lastName;
	private String phone;
	private String eMail;
	private String extraInfo;
	private OrganisationDto group;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public OrganisationDto getGroup() {
		return group;
	}
	public void setGroup(OrganisationDto group) {
		this.group = group;
	}
}
