package se.lexicon.mariahofstam.Presence_CMS.testConverter;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import se.lexicon.mariahofstam.Presence_CMS.converters.EntityDtoConverter;
import se.lexicon.mariahofstam.Presence_CMS.dtos.AttendanceStatusDto;
import se.lexicon.mariahofstam.Presence_CMS.dtos.MemberDto;
import se.lexicon.mariahofstam.Presence_CMS.dtos.NoteDto;
import se.lexicon.mariahofstam.Presence_CMS.dtos.OrganisationDto;
import se.lexicon.mariahofstam.Presence_CMS.dtos.StatusCodeDto;
import se.lexicon.mariahofstam.Presence_CMS.entity.AttendanceStatus;
import se.lexicon.mariahofstam.Presence_CMS.entity.ColorCode;
import se.lexicon.mariahofstam.Presence_CMS.entity.EntityFactory;
import se.lexicon.mariahofstam.Presence_CMS.entity.Member;
import se.lexicon.mariahofstam.Presence_CMS.entity.Note;
import se.lexicon.mariahofstam.Presence_CMS.entity.Organisation;
import se.lexicon.mariahofstam.Presence_CMS.entity.StatusCode;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEntityDtoConverter extends EntityFactory {
	
	@Autowired
	private EntityDtoConverter testObject;
	
	private NoteDto createNoteDto() {
		
		OrganisationDto groupDto = new OrganisationDto();
		groupDto.setId(1);
		groupDto.setGroupName("GroupOne");
		
		StatusCodeDto statusCodeDto = new StatusCodeDto();
		statusCodeDto.setId(1);
		statusCodeDto.setCodeName("status");
		statusCodeDto.setShortDescription("abc");
		statusCodeDto.setColorCode(ColorCode.BLUE);
		
		MemberDto memberDto = new MemberDto();
		memberDto.setId(1);
		memberDto.setActive(true);
		memberDto.setFirstName("Sara");
		memberDto.setLastName("Svensson");
		memberDto.setPhone("0701428579");
		memberDto.seteMail("sara.svensson@test.com");
		memberDto.setExtraInfo("Back problems");
		memberDto.setGroup(groupDto);
		
		AttendanceStatusDto attendanceStatusDto = new AttendanceStatusDto();
		attendanceStatusDto.setId(1);
		attendanceStatusDto.setCreationDateTime(LocalDateTime.now());
		attendanceStatusDto.setMember(memberDto);
		attendanceStatusDto.setCode(statusCodeDto);
		
		NoteDto noteDto = new NoteDto();
		noteDto.setId(1);
		noteDto.setTitle("Title");
		noteDto.setText("Text");
		noteDto.setAttendanceStatus(attendanceStatusDto);
		return noteDto;
	}
	
	private Note createNote() {
		Organisation group = createOrganisation(1, "GroupOne");
		StatusCode code = createStatusCode(1, "status", "abc", ColorCode.BLUE);
		Member member = createMember(1, true, "Sara", "Svensson", "0701428579", "sara.svensson@test.com", "Back problems", group);
		AttendanceStatus status = createAttendanceStatus(1, LocalDateTime.now(), member, code);
		Note note = createNote(1, "Title", "Text", status);
		return note;
	}
	
	private NoteDto dtoNote;
	private Note note;
	
	@Before
	public void setUp() {
		dtoNote = createNoteDto();
		note = createNote();
	}
	
	@Test
	public void given_note_should_return_noteDto() {
		NoteDto actual = testObject.noteToDto(note);
		assertEquals(1, actual.getId());
		assertEquals("Title", actual.getTitle());
		assertEquals("Text", actual.getText());
	}
	
	@Test
	public void given_noteDto_should_return_note() {
		Note actual = testObject.dtoToNote(dtoNote);
		assertEquals(1, actual.getId());
		assertEquals("Title", actual.getTitle());
		assertEquals("Text", actual.getText());
	}
}
