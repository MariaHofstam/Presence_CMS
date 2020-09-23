package se.lexicon.mariahofstam.Presence_CMS.converters;

import org.springframework.stereotype.Component;
import se.lexicon.mariahofstam.Presence_CMS.dtos.*;
import se.lexicon.mariahofstam.Presence_CMS.entity.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityDtoConverter extends EntityFactory {

    public AttendanceStatusDto attendanceStatusToDto(AttendanceStatus attendanceStatus) {
        AttendanceStatusDto dto = new AttendanceStatusDto();
        dto.setId(attendanceStatus.getId());
        dto.setCreationDateTime(attendanceStatus.getCreationDateTime());
        dto.setMember(memberToDto(attendanceStatus.getMember()));
        dto.setCode(statusCodeToDto(attendanceStatus.getCode()));
        return dto;
    }

    public List<AttendanceStatusDto> attendanceStatusToDtos(Iterable<AttendanceStatus> iterable) {
        List<AttendanceStatusDto> dtos = new ArrayList<>();
        for(AttendanceStatus attendanceStatus : iterable) {
            dtos.add(attendanceStatusToDto(attendanceStatus));
        }
        return dtos;
    }

    public AttendanceStatus dtoToAttendanceStatus(AttendanceStatusDto dto) {
        AttendanceStatus attendanceStatus = createAttendanceStatus(
                dto.getId(),
                dto.getCreationDateTime(),
                dtoToMember(dto.getMember()),
                dtoToStatusCode(dto.getCode())
        );
        return attendanceStatus;
    }

    public List<AttendanceStatus> dtosToAttendanceStatus(List<AttendanceStatusDto> dtos) {
        List<AttendanceStatus> attendanceStatus = new ArrayList<>();
        for(AttendanceStatusDto dto : dtos) {
            attendanceStatus.add(dtoToAttendanceStatus(dto));
        }
        return attendanceStatus;
    }

    public MemberDto memberToDto(Member member) {
        MemberDto dto = new MemberDto();
        dto.setId(member.getId());
        dto.setActive(member.isActive());
        dto.setFirstName(member.getFirstName());
        dto.setLastName(member.getLastName());
        dto.setPhone(member.getPhone());
        dto.seteMail(member.geteMail());
        dto.setExtraInfo(member.getExtraInfo());
        dto.setGroup(organisationToDto(member.getGroup()));
        return dto;
    }

    public List<MemberDto> membersToDtos(Iterable<Member> iterable) {
        List<MemberDto> dtos = new ArrayList<>();
        for(Member member : iterable) {
            dtos.add(memberToDto(member));
        }
        return dtos;
    }

    public Member dtoToMember(MemberDto dto) {
        Member member = createMember(
                dto.getId(),
                dto.isActive(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPhone(),
                dto.geteMail(),
                dto.getExtraInfo(),
                dtoToOrganisation(dto.getGroup())
        );
        return member;
    }

    public List<Member> dtosToMembers(List<MemberDto> dtos) {
        List<Member> members = new ArrayList<>();
        for(MemberDto dto : dtos) {
            members.add(dtoToMember(dto));
        }
        return members;
    }

    public NoteDto noteToDto(Note note) {
        NoteDto dto = new NoteDto();
        dto.setId(note.getId());
        dto.setTitle(note.getTitle());
        dto.setText(note.getText());
        dto.setAttendanceStatus(attendanceStatusToDto(note.getAttendanceStatus()));
        return dto;
    }

    public List<NoteDto> notesToDtos(Iterable<Note> iterable) {
        List<NoteDto> dtos = new ArrayList<>();
        for(Note note : iterable) {
            dtos.add(noteToDto(note));
        }
        return dtos;
    }

    public Note dtoToNote(NoteDto dto) {
        Note note = createNote(
                dto.getId(),
                dto.getTitle(),
                dto.getText(),
                dtoToAttendanceStatus(dto.getAttendanceStatus())
        );
        return note;
    }

    public List<Note> dtosToNotes(List<NoteDto> dtos) {
        List<Note> notes = new ArrayList<>();
        for(NoteDto dto : dtos) {
            notes.add(dtoToNote(dto));
        }
        return notes;
    }

    public OrganisationDto organisationToDto(Organisation organisation) {
        OrganisationDto dto = new OrganisationDto();
        dto.setId(organisation.getId());
        dto.setGroupName(organisation.getGroupName());
        return dto;
    }

    public List<OrganisationDto> organisationsToDtos(Iterable<Organisation> iterable) {
        List<OrganisationDto> dtos = new ArrayList<>();
        for(Organisation organisation : iterable) {
            dtos.add(organisationToDto(organisation));
        }
        return dtos;
    }

    public Organisation dtoToOrganisation(OrganisationDto dto) {
        Organisation organisation = createOrganisation(
                dto.getId(),
                dto.getGroupName()
        );
        return organisation;
    }

    public List<Organisation> dtosToOrganisations(List<OrganisationDto> dtos) {
        List<Organisation> organisations = new ArrayList<>();
        for(OrganisationDto dto : dtos) {
            organisations.add(dtoToOrganisation(dto));
        }
        return organisations;
    }

    public StatusCodeDto statusCodeToDto(StatusCode statusCode) {
        StatusCodeDto dto = new StatusCodeDto();
        dto.setId(statusCode.getId());
        dto.setCodeName(statusCode.getCodeName());
        dto.setShortDescription(statusCode.getShortDescription());
        dto.setColorCode(statusCode.getColorCode());
        return dto;
    }

    public List<StatusCodeDto> statusCodesToDto(Iterable<StatusCode> iterable) {
        List<StatusCodeDto> dtos = new ArrayList<>();
        for(StatusCode statusCode : iterable) {
            dtos.add(statusCodeToDto(statusCode));
        }
        return dtos;
    }

    public StatusCode dtoToStatusCode(StatusCodeDto dto) {
        StatusCode statusCode = createStatusCode(
                dto.getId(),
                dto.getCodeName(),
                dto.getShortDescription(),
                dto.getColorCode()
        );
        return statusCode;
    }

    public List<StatusCode> dtosToStatusCodes(List<StatusCodeDto> dtos) {
        List<StatusCode> statusCodes = new ArrayList<>();
        for(StatusCodeDto dto : dtos) {
            statusCodes.add(dtoToStatusCode(dto));
        }
        return statusCodes;
    }
}
