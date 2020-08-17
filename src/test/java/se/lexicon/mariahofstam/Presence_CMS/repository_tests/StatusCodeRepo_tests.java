package se.lexicon.mariahofstam.Presence_CMS.repository_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.mariahofstam.Presence_CMS.entity.StatusCode;
import se.lexicon.mariahofstam.Presence_CMS.repositories.StatusCodeRepo;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Transactional
public class StatusCodeRepo_tests {

    @Autowired
    private StatusCodeRepo testRepo;
    private StatusCode testCode;


    @BeforeEach
    public void init() {
        //Create some StatusCodes
        StatusCode red = new StatusCode("codeRed", "WARNING", "red");
        StatusCode green = new StatusCode("codeGreen", "OKEJ", "green");

       testCode = testRepo.save(red);
       testRepo.save(green);
    }

    @Test
    public void test_findByNameIgnoreCase() {
        List<StatusCode> expected = Arrays.asList(testCode);
        List<StatusCode> actual = testRepo.findByCodeNameIgnoreCaseContaining("codeRed");

        assertEquals(expected, actual);
    }

    @Test
    public void test_findByNameIgnoreCase_all_returned_match_param() {
        String param = "codeGreen";
        List<StatusCode> result = testRepo.findByCodeNameIgnoreCaseContaining(param);

        assertTrue(result.stream()
                .allMatch(code -> code.getCodeName().equalsIgnoreCase(param)));

    }
}
