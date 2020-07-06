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

       //Organisations/groups
        Organisation firstGroup = organisationRepo.save(new Organisation("NumberOne"));
        Organisation secondGroup = organisationRepo.save(new Organisation("NumberTwo"));

        //Status-codes
        StatusCode code1 = statusCodeRepo.save(new StatusCode("status1", "abcd", "yellow"));
        StatusCode code2 = statusCodeRepo.save(new StatusCode("status2", "usttalt","green"));

        //Members, connecting to a group
        Member sara = new Member(true, "Sara", "Peterson", "0708523465", "sara.petersson@abcdef.com", "", firstGroup);
        Member andreas = new Member(true,"Andreas", "Olsson", "0703287645", "andreas.karlsson@gladaladan.se", "diabetes", secondGroup);
        Member gunilla = new Member(true, "Gunilla", "Sjöström", "08653429", "gunillaa.sjostrom@telia.se","seasick", firstGroup);
        Member sven = new Member(false, "Sven", "Karlsson", "0734258790", "sven.karlsson@gmail.com", "", secondGroup);

        //Add member to a group
        firstGroup.addMember(sara);
        firstGroup.addMember(gunilla);
        secondGroup.addMember(andreas);
        secondGroup.addMember(sven);

        //Add Attendance status for a member
        sara.addAttendanceStatus(code1);
        gunilla.addAttendanceStatus(code2);
        andreas.addAttendanceStatus(code1);
        sven.addAttendanceStatus(code2);


//---------------------------------------------------------------------------
        AttendanceStatus status1 = attendanceStatusRepo.save(new AttendanceStatus(LocalDateTime.now(), sara, code1));

        Note abc = noteRepo.save(new Note("Test", "Tja, något ska man väl kunna skriva ihop", status1));





    }
}
