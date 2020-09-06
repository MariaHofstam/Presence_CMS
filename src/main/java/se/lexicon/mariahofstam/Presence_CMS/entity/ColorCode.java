package se.lexicon.mariahofstam.Presence_CMS.entity;

import com.fasterxml.jackson.annotation.JsonGetter;

public enum ColorCode {

    GREEN("Green"),
    YELLOW("Yellow"),
    ORANGE("Orange"),
    RED("Red"),
    BLUE("Blue"),
    PURPLE("Purple");

    private String colorCodes;

    private ColorCode(String colorCodes) {
        this.colorCodes = colorCodes;
    }

    @JsonGetter
    public String getColorCodes() {
        return colorCodes;
    }
}
