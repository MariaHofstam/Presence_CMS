package se.lexicon.mariahofstam.Presence_CMS.repositories;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.mariahofstam.Presence_CMS.entity.AttendanceStatus;
import se.lexicon.mariahofstam.Presence_CMS.entity.Member;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceStatusRepo extends CrudRepository<AttendanceStatus, Integer> {
    List<AttendanceStatus> findByMember(Member member);
    List<AttendanceStatus> findByCreationDateTime(LocalDateTime someDateTime);
    List<AttendanceStatus> findByCreationDateTimeBefore(LocalDateTime someDateTime); //used in AttendanceStatusRepo_tests
}
