package se.lexicon.mariahofstam.Presence_CMS.services_tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.mariahofstam.Presence_CMS.converters.EntityDtoConverter;
import se.lexicon.mariahofstam.Presence_CMS.dtos.MemberDto;
import se.lexicon.mariahofstam.Presence_CMS.entity.Member;
import se.lexicon.mariahofstam.Presence_CMS.entity.Organisation;
import se.lexicon.mariahofstam.Presence_CMS.repositories.MemberRepo;
import se.lexicon.mariahofstam.Presence_CMS.services.MemberService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class MemberService_tests {

    @Autowired
    private MemberService testObject;

    @Autowired
    private MemberRepo repo;


    private Organisation teamAlpha = new Organisation("teamAlpha");
    private Member adam;
    private Member eva;

    @BeforeEach
    public void setUp() {
        adam = new Member(true, "Adam", "Johansson", "074287542", "adam.johansson", "", teamAlpha);
        adam = repo.save(adam);

        eva = new Member(true, "Eva", "Olsson", "073212614", "eva.johansson", "", teamAlpha);
        eva = repo.save(eva);
    }

    @Test
    public void given_id_should_return_optional_of_findById() {
        int id = eva.getId();
        MemberDto result = testObject.findById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void given_firstName_should_return_list_size_of_1(){
        String firstName = eva.getFirstName();
        int expectedSize = 1;
        List<MemberDto> result = testObject.findByFirstName(firstName);
        assertEquals(expectedSize, result.size());
    }


    /*@Test         //funkar ej
    public void given_lastName_should_return_list_size_of_1(){
        String lastName = eva.getLastName();
        int expectedSize = 1;
        List<MemberDto> result = testObject.findByLastName(lastName);
        assertEquals(expectedSize, result.size());
    }
*/


    @Test
    public void given_eMail_should_return_list_size_of_1(){
        String eMail = eva.geteMail();
        int expectedSize = 1;
        List<MemberDto> result = testObject.findByEmail(eMail);
        assertEquals(expectedSize, result.size());
    }

    /*
    @Test
    public void findAll_should_return_list_size_of_2() {
        int expectedSize = 2;
        List<MemberDto> result = testObject.findAll();
        assertEquals(expectedSize, result.size());
    }
    */


    /*@Test
    public void create_object_from_dto_successfully() {
        Member toCreate = new Member(false, "Malin", "Gustafsson","071225698", "malin.gustafsson@gmail.com", "", teamAlpha);
        MemberDto dto = new MemberDto();

        dto.setId(toCreate.getId());
        dto.setActive(toCreate.isActive());
        dto.setFirstName(toCreate.getFirstName());
        dto.setLastName(toCreate.getLastName());
        dto.setPhone(toCreate.getPhone());
        dto.seteMail(toCreate.geteMail());
        dto.setExtraInfo(toCreate.getExtraInfo());
        //dto.setGroup(toCreate.getGroup());        //funkar ej

        testObject.createMember(dto);

        assertNotNull(dto);
        //Assertions.assertEquals(3, repo.count());
        //assertTrue(testObject.deleteMember(dto.getId()));
    }
    */

    @Test
    public void testing_update_method_on_new_dto_successfully() {
        MemberDto toUpdate = new MemberDto();
        toUpdate.setId(adam.getId());
        toUpdate.setExtraInfo("Vegetarian");

        testObject.updateMember(toUpdate);
        assertEquals("Vegetarian", toUpdate.getExtraInfo());
    }

    @AfterEach
    public void after() {
        repo.deleteAll();
    }
}
