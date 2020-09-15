package se.lexicon.mariahofstam.Presence_CMS.services;

import se.lexicon.mariahofstam.Presence_CMS.dtos.StatusCodeDto;

import java.util.List;
import java.util.NoSuchElementException;

public interface StatusCodeService {

    StatusCodeDto findById(int id);
    List<StatusCodeDto> findAll() throws NoSuchElementException;
    List<StatusCodeDto> findByName(String codeName) throws NoSuchElementException;

    StatusCodeDto createStatusCode(StatusCodeDto statusCodeDto);
    StatusCodeDto updateStatusCode(StatusCodeDto statusCodeDto);
    boolean deleteStatusCode(int id);
}
