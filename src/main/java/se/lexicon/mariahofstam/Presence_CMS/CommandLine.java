package se.lexicon.mariahofstam.Presence_CMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.mariahofstam.Presence_CMS.entity.*;
import se.lexicon.mariahofstam.Presence_CMS.repositories.*;

import java.time.LocalDateTime;

@Component
@Transactional(rollbackFor = Exception.class)
public class CommandLine implements CommandLineRunner {

    private AttendanceStatusRepo attendanceStatusRepo;
    private MemberRepo memberRepo;
    private NoteRepo noteRepo;
    private OrganisationRepo organisationRepo;
    private StatusCodeRepo statusCodeRepo;

    @Autowired
    public CommandLine(AttendanceStatusRepo attendanceStatusRepo, MemberRepo memberRepo, NoteRepo noteRepo,
                       OrganisationRepo organisationRepo, StatusCodeRepo statusCodeRepo) {
        this.attendanceStatusRepo = attendanceStatusRepo;
        this.memberRepo = memberRepo;
        this.noteRepo = noteRepo;
        this.organisationRepo = organisationRepo;
        this.statusCodeRepo = statusCodeRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        Organisation firstGroup = organisationRepo.save(new Organisation("NumberOne"));
        Organisation secondGroup = organisationRepo.save(new Organisation("NumberTwo"));

        AttendanceStatus status1 = attendanceStatusRepo.save(new AttendanceStatus(LocalDateTime.now()));

        StatusCode code1 = statusCodeRepo.save(new StatusCode("status1", "abcd", "yellow"));

        Note abc = noteRepo.save(new Note("title1", "text1"));

        Member Sara = memberRepo.save(new Member(true, "Sara", "Petersson",
                "0708523465", "sara.petersson@abcdef.com", ""));


    }
}
