package se.lexicon.mariahofstam.Presence_CMS.services;

import se.lexicon.mariahofstam.Presence_CMS.dtos.OrganisationDto;

import java.util.List;
import java.util.NoSuchElementException;

public interface OrganisationService {

    OrganisationDto findById(int id);
    List<OrganisationDto> findAll() throws NoSuchElementException;
    List<OrganisationDto> findByName(String groupName) throws NoSuchElementException;

    OrganisationDto createOrganisation(OrganisationDto organisationDto);
    OrganisationDto updateOrganisation(OrganisationDto organisationDto);
    boolean deleteOrganisation(int id);
}
