package se.lexicon.mariahofstam.Presence_CMS.repositories;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.mariahofstam.Presence_CMS.entity.Organisation;

import java.util.List;

public interface OrganisationRepo extends CrudRepository<Organisation, Integer> {
    List<Organisation> findByGroupNameIgnoreCaseContaining(String name);
}
