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
public class MemberRepo_tests {

    @Autowired
    private MemberRepo testRepo;

    private Member testMember;
    private Organisation teamAlpha = new Organisation("teamAlpha");

    @BeforeEach
    public void init() {

        //Create some Members
        Member annika = new Member(true, "Annika", "Karlsson", "0734257529", "annika.karlsson@gmail.com","No info", teamAlpha);
        Member pelle = new Member(true, "Per", "Olsson", "0731738450", "per.olsson@gmail.com", "", teamAlpha);

        testMember = testRepo.save(annika);
        testRepo.save(pelle);
    }

    @Test
    public void test_findByFirstNameIgnoreCase() {
        List<Member> expected = Arrays.asList(testMember);
        List<Member> actual = testRepo.findByFirstNameIgnoreCaseContaining("Annika");
        assertEquals(expected, actual);
    }

    @Test
    public void test_findByNameIgnoreCase_all_returned_match_param() {
        String param = "Olsson";
        List<Member> result = testRepo.findByLastNameIgnoreCaseContaining(param);

        assertTrue(result.stream()
                .allMatch(person -> person.getLastName().equalsIgnoreCase(param)));

    }
}
