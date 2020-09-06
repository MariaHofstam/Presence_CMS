package se.lexicon.mariahofstam.Presence_CMS.repository_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Transactional
public class OrganisationRepo_tests {

    @Autowired
    private OrganisationRepo testRepo;
    private Organisation testGroup;

    @BeforeEach
    public void init() {

        //Create some Organisations
        Organisation teamAlpha = new Organisation("teamAlpha");
        Organisation teamOmega = new Organisation("teamOmega");

        testGroup = testRepo.save(teamAlpha);
        testRepo.save(teamOmega);
    }

    @Test
    public void test_findByNameIgnoreCase() {
        List<Organisation> expected = Arrays.asList(testGroup);
        List<Organisation> actual = testRepo.findByGroupNameIgnoreCaseContaining("teamAlpha");
        assertEquals(expected, actual);
    }


    @Test
    public void test_findByNameIgnoreCase_all_returned_match_param() {
        String param = "teamOmega";
        List<Organisation> result = testRepo.findByGroupNameIgnoreCaseContaining(param);

        assertTrue(result.stream()
                .allMatch(team -> team.getGroupName().equalsIgnoreCase(param)));

    }


}
