package se.lexicon.mariahofstam.Presence_CMS.services_tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.mariahofstam.Presence_CMS.dtos.NoteDto;
import se.lexicon.mariahofstam.Presence_CMS.entity.*;
import se.lexicon.mariahofstam.Presence_CMS.repositories.NoteRepo;
import se.lexicon.mariahofstam.Presence_CMS.services.NoteService;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class NoteService_tests {

    @Autowired
    private NoteService testObject;

    @Autowired
    private NoteRepo repo;

    private Note firstTraining;
    private Note secondTraining;

    private Organisation teamAlpha = new Organisation("teamAlpha");
    private Member annika = new Member(true, "Annika", "Karlsson", "0734257529", "annika.karlsson@gmail.com","No info", teamAlpha);
    private StatusCode code1 = new StatusCode("status1", "abcd", ColorCode.YELLOW);

    private LocalDateTime param1 = LocalDateTime.of(2020, 8, 9, 14, 20);
    private LocalDateTime param2 = LocalDateTime.of(2020, 8, 10, 13, 15);

    @BeforeEach
    public void setUp() {

        //Create attendanceStatuses
        AttendanceStatus status1 = new AttendanceStatus(param1, annika, code1);
        AttendanceStatus status2 = new AttendanceStatus(param2, annika, code1);

        firstTraining = new Note("First Training","This was Annikas first training session", status1);
        firstTraining = repo.save(firstTraining);

        secondTraining = new Note("Second Training","This was Annikas second training session", status2);
        secondTraining = repo.save(secondTraining);

    }

    @Test
    public void given_id_should_return_optional_of_findById() {
        int id = firstTraining.getId();
        NoteDto result = testObject.findById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void given_Name_should_return_list_size_of_1() {
        String title = firstTraining.getTitle();
        int expectedSize = 1;
        List<NoteDto> result = testObject.findByName(title);
        assertEquals(expectedSize, result.size());
    }

    /*@Test
    public void findAll_should_return_list_size_of_2() {
        int expectedSize = 2;
        List<NoteDto> result = testObject.findAll();
        assertEquals(expectedSize, result.size());
    }
    */

    /*
    @Test
    public void create_object_from_dto_successfully() {
        LocalDateTime param3 = LocalDateTime.of(2020, 9, 2, 11, 17);
        AttendanceStatus status3 = new AttendanceStatus(param3, annika, code1);

        Note toCreate = new Note("Third Training","This was Annikas third training session", status3);
        NoteDto dto = new NoteDto();

        dto.setId(toCreate.getId());
        dto.setTitle(toCreate.getTitle());
        dto.setText(toCreate.getText());
        //dto.setAttendanceStatus(toCreate.getAttendanceStatus());  funkar ej

        testObject.createNote(dto);

        assertNotNull(dto);
        //Assertions.assertEquals(3, repo.count());
        //assertTrue(testObject.deleteNote(dto.getId()));
    }
    */

    @Test
    public void testing_update_method_on_new_dto_successfully() {
        NoteDto toUpdate = new NoteDto();

        toUpdate.setId(firstTraining.getId());
        toUpdate.setText("This was Annikas first training session after accident");

        testObject.updateNote(toUpdate);
        assertEquals("This was Annikas first training session after accident", toUpdate.getText());
    }


    @AfterEach
    public void after() {
        repo.deleteAll();
    }
}
