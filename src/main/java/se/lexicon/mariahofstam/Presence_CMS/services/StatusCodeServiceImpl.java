package se.lexicon.mariahofstam.Presence_CMS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.mariahofstam.Presence_CMS.converters.EntityDtoConverter;
import se.lexicon.mariahofstam.Presence_CMS.dtos.StatusCodeDto;
import se.lexicon.mariahofstam.Presence_CMS.entity.StatusCode;
import se.lexicon.mariahofstam.Presence_CMS.repositories.StatusCodeRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class StatusCodeServiceImpl implements StatusCodeService {

    private StatusCodeRepo repo;
    private EntityDtoConverter converter;

    @Autowired
    public StatusCodeServiceImpl(StatusCodeRepo repo, EntityDtoConverter converter) {
        this.repo = repo;
        this.converter = converter;
    }


    @Override
    public StatusCodeDto findById(int id) {

        Optional<StatusCode> result = repo.findById(id);
        StatusCode statusCode = result.get();
        return converter.statusCodeToDto(statusCode);
    }

    @Override
    public List<StatusCodeDto> findAll() throws NoSuchElementException {
        List<StatusCode> codes = (List<StatusCode>) repo.findAll();

        if (codes.isEmpty()){
            throw new NoSuchElementException("There are no codes in the database.");
        } else{
            return converter.statusCodesToDto(codes);
        }
    }

    @Override
    public List<StatusCodeDto> findByName(String codeName) throws NoSuchElementException {
        List<StatusCode> codes = repo.findByCodeNameIgnoreCaseContaining(codeName);

        if (codes.isEmpty()){
            throw new NoSuchElementException("There is no code with the name - " + codeName + " in the database.");
        } else{
            return converter.statusCodesToDto(codes);
        }
    }

    @Override
    public StatusCodeDto createStatusCode(StatusCodeDto statusCodeDto) {
        StatusCode statusCode = converter.dtoToStatusCode(statusCodeDto);   //Create StatusCode-object from DTO
        repo.save(statusCode);                                              //Save to database
        return converter.statusCodeToDto(statusCode);                       //Return dto
    }

    @Override
    public StatusCodeDto updateStatusCode(StatusCodeDto statusCodeDto) {
        StatusCode original = repo.findById(statusCodeDto.getId()).get();
        original.setCodeName(statusCodeDto.getCodeName());
        original.setColorCode(statusCodeDto.getColorCode());
        original.setShortDescription(statusCodeDto.getShortDescription());
        repo.save(original);
        return statusCodeDto;
    }

    @Override
    public boolean deleteStatusCode(int id) {
        repo.deleteById(id);
        return repo.existsById(id);                  // Boolean exist or not?
    }
}
