package se.lexicon.mariahofstam.Presence_CMS.entity;

import java.util.Objects;

public class StatusCode {
    private int id;
    private String codeName;
    private String shortDescription;
    private String colorCode;

    // Constructors

    public StatusCode(){}

    public StatusCode(String codeName, String shortDescription, String colorCode) {
        this.codeName = codeName;
        this.shortDescription = shortDescription;
        this.colorCode = colorCode;
    }

    //Getters and Setters


    public int getId() {
        return id;
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

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    @Override
    public String toString() {
        return "StatusCode{" +
                "id=" + id +
                ", codeName='" + codeName + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", colorCode='" + colorCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatusCode)) return false;
        StatusCode that = (StatusCode) o;
        return id == that.id &&
                Objects.equals(codeName, that.codeName) &&
                Objects.equals(shortDescription, that.shortDescription) &&
                Objects.equals(colorCode, that.colorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codeName, shortDescription, colorCode);
    }
}
