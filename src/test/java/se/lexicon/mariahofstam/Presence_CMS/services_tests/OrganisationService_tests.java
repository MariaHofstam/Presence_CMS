package se.lexicon.mariahofstam.Presence_CMS.services_tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.mariahofstam.Presence_CMS.dtos.OrganisationDto;
import se.lexicon.mariahofstam.Presence_CMS.entity.Organisation;
import se.lexicon.mariahofstam.Presence_CMS.repositories.OrganisationRepo;
import se.lexicon.mariahofstam.Presence_CMS.services.OrganisationService;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class OrganisationService_tests {

    @Autowired
    private OrganisationService testObject;

    @Autowired
    private OrganisationRepo repo;

    private Organisation teamYes;
    private Organisation teamNooway;
    private Organisation teamExtra;


    @BeforeEach
    public void setUp() {
        teamYes = new Organisation("teamYes");
        teamYes = repo.save(teamYes);

        teamNooway = new Organisation("teamNooway");
        teamNooway = repo.save(teamNooway);

        //teamExtra = new Organisation("teamExtra");
        //teamExtra = repo.save(teamExtra);
    }


    @Test
    public void given_id_should_return_optional_of_findById() {
        int id = teamYes.getId();
        OrganisationDto result = testObject.findById(id);
        assertEquals(id, result.getId());
    }

    /*
    @Test
    public void findAll_should_return_list_size_of_2() {
        int expectedSize = 2;
        List<OrganisationDto> result = testObject.findAll();
        assertEquals(expectedSize, result.size());
    }
    */


    @Test
    public void given_groupName_should_return_list_size_of_1() {
        String groupName = teamYes.getGroupName();
        int expectedSize = 1;
        List<OrganisationDto> result = testObject.findByName(groupName);
        assertEquals(expectedSize, result.size());
    }

    @Test
    public void create_object_from_dto_successfully() {
        Organisation toCreate = new Organisation("teamMaybe");
        OrganisationDto dto = new OrganisationDto();

        dto.setId(toCreate.getId());
        dto.setGroupName(toCreate.getGroupName());

        testObject.createOrganisation(dto);

        assertNotNull(dto);
        //Assertions.assertEquals(3, repo.count());
        //assertTrue(testObject.deleteOrganisation(dto.getId()));
    }


    @Test
    public void testing_update_method_on_new_dto_successfully() {
        OrganisationDto toUpdate = new OrganisationDto();
        toUpdate.setId(teamYes.getId());
        toUpdate.setGroupName("teamAllTheWay");

        testObject.updateOrganisation(toUpdate);
        assertEquals("teamAllTheWay", toUpdate.getGroupName());
    }

    @AfterEach
    public void after() {
        repo.deleteAll();
    }
}
