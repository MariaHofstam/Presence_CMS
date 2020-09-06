package se.lexicon.mariahofstam.Presence_CMS.repository_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.mariahofstam.Presence_CMS.entity.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Transactional
public class AttendanceStatusRepo_tests {

    @Autowired
    private AttendanceStatusRepo testRepo;

    private AttendanceStatus testStatus;

    private Organisation teamAlpha = new Organisation("teamAlpha");
    private Member annika = new Member(true, "Annika", "Karlsson", "0734257529", "annika.karlsson@gmail.com","No info", teamAlpha);
    private Member pelle = new Member(true, "Per", "Olsson", "0731738450", "per.olsson@gmail.com", "", teamAlpha);
    private StatusCode code1 = new StatusCode("status1", "abcd", "yellow");

    private LocalDateTime param1 = LocalDateTime.of(2020, 8, 9, 14, 20);
    private LocalDateTime param2 = LocalDateTime.of(2020, 8, 10, 13, 15);

    @BeforeEach
    public void init() {

        //Create some attendanceStatuses
        AttendanceStatus status1 = new AttendanceStatus(param1, annika, code1);
        AttendanceStatus status2 = new AttendanceStatus(param2, pelle, code1);

        testStatus = testRepo.save(status1);
        testRepo.save(status2);
    }

    @Test
    public void test_findByMember() {

        List<AttendanceStatus> expected = Arrays.asList(testStatus);
        List<AttendanceStatus> actual = testRepo.findByMember(annika);

        assertEquals(expected, actual);
    }

    @Test
    public void test_findByCreationDate() {

        LocalDateTime param = LocalDateTime.of(2020, 8, 14, 8, 53);
        int expectedSize = 2; //See BeforeEach

        List<AttendanceStatus> result = testRepo.findByCreationDateTimeBefore(param);
        assertEquals(expectedSize, result.size());

        assertTrue(result.stream()
                .map(AttendanceStatus::getCreationDateTime)
                .allMatch(time -> time.isBefore(param)));

    }
}
