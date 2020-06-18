package se.lexicon.mariahofstam.Presence_CMS.repositories;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.mariahofstam.Presence_CMS.entity.AttendanceStatus;

public interface AttendanceStatusRepo extends CrudRepository<AttendanceStatus, Integer> {
}
