package se.lexicon.mariahofstam.Presence_CMS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.mariahofstam.Presence_CMS.converters.EntityDtoConverter;
import se.lexicon.mariahofstam.Presence_CMS.dtos.OrganisationDto;
import se.lexicon.mariahofstam.Presence_CMS.entity.Organisation;
import se.lexicon.mariahofstam.Presence_CMS.repositories.OrganisationRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class OrganisationServiceImpl implements OrganisationService {

    private OrganisationRepo repo;
    private EntityDtoConverter converter;

    @Autowired
    public OrganisationServiceImpl(OrganisationRepo repo, EntityDtoConverter converter) {
        this.repo = repo;
        this.converter = converter;
    }

    @Override
    public OrganisationDto findById(int id) {
        Optional<Organisation> result = repo.findById(id);
        Organisation organisation = result.get();
        return converter.organisationToDto(organisation);
    }

    @Override
    public List<OrganisationDto> findAll() throws NoSuchElementException {
        List<Organisation> groups = (List<Organisation>) repo.findAll();

        if (groups.isEmpty()){
            throw new NoSuchElementException("There are no organisations in the database.");
        } else{
            return converter.organisationsToDtos(groups);
        }
    }

    @Override
    public List<OrganisationDto> findByName(String groupName) throws NoSuchElementException {
        List<Organisation> groups = repo.findByGroupNameIgnoreCaseContaining(groupName);

        if (groups.isEmpty()){
            throw new NoSuchElementException("There is no organisation with the name - " + groupName + " in the database.");
        } else{
            return converter.organisationsToDtos(groups);
        }
    }



    @Override
    public OrganisationDto createOrganisation(OrganisationDto organisationDto) {
        Organisation organisation = converter.dtoToOrganisation(organisationDto);   //Create Organisation-object from DTO
        repo.save(organisation);                                                    //Save to database
        return converter.organisationToDto(organisation);                           //Return dto
    }

    @Override
    public OrganisationDto updateOrganisation(OrganisationDto organisationDto) {
       Organisation original = repo.findById(organisationDto.getId()).get();
       original.setGroupName(organisationDto.getGroupName());
       repo.save(original);
       return organisationDto;
    }

    @Override
    public boolean deleteOrganisation(int id) {
        repo.deleteById(id);
        return repo.existsById(id);                  // Boolean exist or not?
    }
}
