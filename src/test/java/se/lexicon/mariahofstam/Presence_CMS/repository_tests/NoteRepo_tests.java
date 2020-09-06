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
public class NoteRepo_tests {

    @Autowired
    private NoteRepo testRepo;

    private Note testNote;

    private Organisation teamAlpha = new Organisation("teamAlpha");
    private Member annika = new Member(true, "Annika", "Karlsson", "0734257529", "annika.karlsson@gmail.com","No info", teamAlpha);
    private StatusCode code1 = new StatusCode("status1", "abcd", "yellow");


    @BeforeEach
    public void init() {

        //Create an attendanceStatus
        AttendanceStatus status1 = new AttendanceStatus(LocalDateTime.now(), annika, code1);

        //Create some Notes
        Note note1 = new Note("Note1", "Tja, något ska man väl kunna skriva ihop", status1);
        Note note2 = new Note("Test2", "Ja, det var ju det där med att man skulle skriva ihop något", status1);

        testNote = testRepo.save(note1);
        testRepo.save(note2);
    }

    @Test
    public void test_findByTitleIgnoreCase() {

        List<Note> expected = Arrays.asList(testNote);
        List<Note> actual = testRepo.findByTitleIgnoreCaseContaining("Note1");
        assertEquals(expected, actual);
    }

    @Test
    public void test_findByNameIgnoreCase_all_returned_match_param() {
        String param = "Test2";
        List<Note> result = testRepo.findByTitleIgnoreCaseContaining(param);

        assertTrue(result.stream()
                .allMatch(title -> title.getTitle().equalsIgnoreCase(param)));
    }

}
