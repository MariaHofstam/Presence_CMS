package se.lexicon.mariahofstam.Presence_CMS.dtos;

import se.lexicon.mariahofstam.Presence_CMS.entity.ColorCode;

public class StatusCodeDto {

	private int id;
	private String codeName;
	private String shortDescription;
	private ColorCode colorCode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public ColorCode getColorCode() {
		return colorCode;
	}
	public void setColorCode(ColorCode colorCode) {
		this.colorCode = colorCode;
	}
}
