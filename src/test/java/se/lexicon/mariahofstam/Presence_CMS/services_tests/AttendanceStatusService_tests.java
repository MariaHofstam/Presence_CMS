package se.lexicon.mariahofstam.Presence_CMS.services_tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.mariahofstam.Presence_CMS.dtos.AttendanceStatusDto;
import se.lexicon.mariahofstam.Presence_CMS.entity.*;
import se.lexicon.mariahofstam.Presence_CMS.repositories.AttendanceStatusRepo;
import se.lexicon.mariahofstam.Presence_CMS.services.AttendanceStatusService;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AttendanceStatusService_tests {

    @Autowired
    private AttendanceStatusService testObject;

    @Autowired
    private AttendanceStatusRepo repo;

    private AttendanceStatus first;
    private AttendanceStatus second;

    private Organisation teamAlpha = new Organisation("teamAlpha");
    private Member annika = new Member(true, "Annika", "Karlsson", "0734257529", "annika.karlsson@gmail.com","No info", teamAlpha);
    private Member pelle = new Member(true, "Per", "Olsson", "0731738450", "per.olsson@gmail.com", "", teamAlpha);
    private StatusCode code1 = new StatusCode("status1", "abcd", ColorCode.GREEN);
    private StatusCode code2 = new StatusCode("status2", "ABCD", ColorCode.RED);

    private LocalDateTime param1 = LocalDateTime.of(2020, 8, 9, 14, 20);
    private LocalDateTime param2 = LocalDateTime.of(2020, 8, 10, 13, 15);

    @BeforeEach
    public void setUp() {

        first = new AttendanceStatus(param1, annika, code1);
                first = repo.save(first);

        second = new AttendanceStatus(param2, pelle, code1);
                second = repo.save(second);
    }

    @Test
    public void given_id_should_return_optional_of_findById() {
        int id = first.getId();
        AttendanceStatusDto result = testObject.findById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void given_Member_should_return_list_size_of_1(){
        Member member = first.getMember();
        int expectedSize = 1;
        List<AttendanceStatusDto> result = testObject.findByMember(member);
        assertEquals(expectedSize, result.size());
    }

    @Test
    public void given_CreationDateTime_should_return_list_size_of_1(){
        LocalDateTime someDateTime = first.getCreationDateTime();
        int expectedSize = 1;
        List<AttendanceStatusDto> result = testObject.findByCreationDate(someDateTime);
        assertEquals(expectedSize, result.size());
    }

    /*@Test
    public void findAll_should_return_list_size_of_2() {
        int expectedSize = 2;
        List<AttendanceStatusDto> result = testObject.findAll();
        assertEquals(expectedSize, result.size());
    }*/


   /* @Test
    public void create_object_from_dto_successfully() {
        LocalDateTime param3 = LocalDateTime.of(2020, 9, 3, 11, 23);

        AttendanceStatus toCreate = new AttendanceStatus(param3, annika, code1);
        AttendanceStatusDto dto = new AttendanceStatusDto();

        dto.setId(toCreate.getId());
        dto.setCreationDateTime(toCreate.getCreationDateTime());
        //dto.setMember(toCreate.getMember());  funkar ej
        //dto.setCode(toCreate.getCode());      funkar ej

        testObject.createAttendanceStatus(dto);

        //assertNotNull(dto);
        //Assertions.assertEquals(3, repo.count());
        assertTrue(testObject.deleteAttendanceStatus(dto.getId()));
    }
    */

    @AfterEach
    public void after() {
        repo.deleteAll();
    }
}
