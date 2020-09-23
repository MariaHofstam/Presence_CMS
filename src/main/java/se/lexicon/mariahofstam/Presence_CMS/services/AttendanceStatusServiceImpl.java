package se.lexicon.mariahofstam.Presence_CMS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.mariahofstam.Presence_CMS.converters.EntityDtoConverter;
import se.lexicon.mariahofstam.Presence_CMS.dtos.AttendanceStatusDto;
import se.lexicon.mariahofstam.Presence_CMS.entity.AttendanceStatus;
import se.lexicon.mariahofstam.Presence_CMS.entity.Member;
import se.lexicon.mariahofstam.Presence_CMS.repositories.AttendanceStatusRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class AttendanceStatusServiceImpl implements AttendanceStatusService {

    private AttendanceStatusRepo repo;
    private EntityDtoConverter converter;

    @Autowired
    public AttendanceStatusServiceImpl(AttendanceStatusRepo repo, EntityDtoConverter converter) {
        this.repo = repo;
        this.converter = converter;
    }

    @Override
    public AttendanceStatusDto findById(int id) {
        Optional<AttendanceStatus> result = repo.findById(id);
        AttendanceStatus attendanceStatus = result.get();
        return converter.attendanceStatusToDto(attendanceStatus);
    }

    @Override
    public List<AttendanceStatusDto> findAll() throws NoSuchElementException {
        List<AttendanceStatus> statuses = (List<AttendanceStatus>) repo.findAll();

        if (statuses.isEmpty()){
            throw new NoSuchElementException("There are no statuses in the database.");
        } else{
            return  converter.attendanceStatusToDtos(statuses);
        }
    }

    @Override
    public List<AttendanceStatusDto> findByMember(Member member) throws NoSuchElementException {
        List<AttendanceStatus> statuses = repo.findByMember(member);

        if (statuses.isEmpty()){
            throw new NoSuchElementException("There is no attendance for the member - " + member + " in the database.");
        } else{
            return converter.attendanceStatusToDtos(statuses);
        }

    }

    @Override
    public List<AttendanceStatusDto> findByCreationDate(LocalDateTime someDateTime) throws NoSuchElementException {
        List<AttendanceStatus> statuses = repo.findByCreationDateTimeBefore(someDateTime);

        if (statuses.isEmpty()){
            throw new NoSuchElementException("There is no attendance for the date - " + someDateTime + " in the database.");
        } else{
            return converter.attendanceStatusToDtos(statuses);
        }
    }

    @Override
    public AttendanceStatusDto createAttendanceStatus(AttendanceStatusDto attendanceStatusDto) {
        AttendanceStatus attendanceStatus = converter.dtoToAttendanceStatus(attendanceStatusDto);   //Create AttendanceStatus-object from DTO
        repo.save(attendanceStatus);                                                                //Save to database
        return converter.attendanceStatusToDto(attendanceStatus);                                   //Return dto
    }


    @Override
    public boolean deleteAttendanceStatus(int id) {
        repo.deleteById(id);
        return repo.existsById(id);                  // Boolean exist or not?
    }
}
