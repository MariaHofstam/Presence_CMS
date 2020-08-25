package se.lexicon.mariahofstam.Presence_CMS.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class StatusCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String codeName;
    private String shortDescription;
    private ColorCode colorCode;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY,
            mappedBy = "code",     //declared in class AttendanceStatus, a @ManyToOne
            orphanRemoval = true
    )
    private List<AttendanceStatus> attendanceStatusList;    //List of attendanceStatuses for this status-code


    // Constructors

    public StatusCode(){}
    
    public StatusCode(int id, String codeName, String shortDescription, ColorCode colorCode) {
    	this(codeName, shortDescription, colorCode);
    	this.id = id;
    }

    public StatusCode(String codeName, String shortDescription, ColorCode colorCode) {
        this.codeName = codeName;
        this.shortDescription = shortDescription;
        this.colorCode = colorCode;
        this.attendanceStatusList = new ArrayList<>();
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


    public ColorCode getColorCode() {
        return colorCode;
    }

    public void setColorCode(ColorCode colorCode) {
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
