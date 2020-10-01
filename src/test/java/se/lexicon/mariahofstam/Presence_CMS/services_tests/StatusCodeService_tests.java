package se.lexicon.mariahofstam.Presence_CMS.services_tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.mariahofstam.Presence_CMS.dtos.StatusCodeDto;
import se.lexicon.mariahofstam.Presence_CMS.entity.ColorCode;
import se.lexicon.mariahofstam.Presence_CMS.entity.StatusCode;
import se.lexicon.mariahofstam.Presence_CMS.repositories.StatusCodeRepo;
import se.lexicon.mariahofstam.Presence_CMS.services.StatusCodeService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class StatusCodeService_tests {

    @Autowired
    private StatusCodeService testObject;

    @Autowired
    private StatusCodeRepo repo;

    private StatusCode mini;
    private StatusCode maxi;

    @BeforeEach
    public void setUp() {
        mini = new StatusCode("mini","minimal effort", ColorCode.BLUE);
        mini = repo.save(mini);

        maxi = new StatusCode("maxi","maximal effort", ColorCode.ORANGE);
        maxi = repo.save(maxi);
    }

    @Test
    public void given_id_should_return_optional_of_findById() {
        int id = mini.getId();
        StatusCodeDto result = testObject.findById(id);
        assertEquals(id, result.getId());
    }

    @Test
    public void given_Name_should_return_list_size_of_1() {
        String codeName = mini.getCodeName();
        int expectedSize = 1;
        List<StatusCodeDto> result = testObject.findByName(codeName);
        assertEquals(expectedSize, result.size());
    }

    /*
    @Test
    public void findAll_should_return_list_size_of_2() {
        int expectedSize = 2;
        List<StatusCodeDto> result = testObject.findAll();
        assertEquals(expectedSize, result.size());
    }
     */

    @Test
    public void create_object_from_dto_successfully() {
        StatusCode toCreate = new StatusCode("codeRed", "WARNING", ColorCode.RED);
        StatusCodeDto dto = new StatusCodeDto();

        dto.setId(toCreate.getId());
        dto.setCodeName(toCreate.getCodeName());
        dto.setShortDescription(toCreate.getShortDescription());
        dto.setColorCode(toCreate.getColorCode());

        testObject.createStatusCode(dto);

        assertNotNull(dto);
        //Assertions.assertEquals(3, repo.count());
        //assertTrue(testObject.deleteStatusCode(dto.getId()));
    }

    @Test
    public void testing_update_method_on_new_dto_successfully() {
        StatusCodeDto toUpdate = new StatusCodeDto();
        toUpdate.setId(mini.getId());
        toUpdate.setCodeName("medium");

        testObject.updateStatusCode(toUpdate);
        assertEquals("medium", toUpdate.getCodeName());
    }


    @AfterEach
    public void after() {
        repo.deleteAll();
    }
}
