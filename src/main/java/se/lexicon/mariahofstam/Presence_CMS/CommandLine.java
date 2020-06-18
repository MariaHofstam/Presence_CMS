package se.lexicon.mariahofstam.Presence_CMS;

import org.springframework.boot.CommandLineRunner;
import se.lexicon.mariahofstam.Presence_CMS.entity.*;

import java.time.LocalDateTime;

public class CommandLine implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        Organisation firstGroup = new Organisation("NumberOne");
        Organisation secondGroup = new Organisation("NumberTwo");

        AttendanceStatus status1 = new AttendanceStatus(LocalDateTime.now());

        StatusCode code1 = new StatusCode("status1", "abcd", "yellow");

        Note abc = new Note("title1", "text1");

        Member Sara = new Member(true, "Sara", "Petersson", "0708523465", "sara.petersson@abcdef.com", "");


    }
}
