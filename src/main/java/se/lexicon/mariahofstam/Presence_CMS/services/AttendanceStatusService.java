package se.lexicon.mariahofstam.Presence_CMS.services;

import se.lexicon.mariahofstam.Presence_CMS.dtos.AttendanceStatusDto;
import se.lexicon.mariahofstam.Presence_CMS.entity.Member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

public interface AttendanceStatusService {

    AttendanceStatusDto findById(int id);
    List<AttendanceStatusDto> findAll() throws NoSuchElementException;
    List<AttendanceStatusDto> findByMember(Member member) throws NoSuchElementException;
    List<AttendanceStatusDto> findByCreationDate(LocalDateTime someDateTime) throws NoSuchElementException;

    AttendanceStatusDto createAttendanceStatus(AttendanceStatusDto attendanceStatusDto);
    //Should it be possible to update anything here?
    boolean deleteAttendanceStatus(int id);
}
