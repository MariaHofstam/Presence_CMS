package se.lexicon.mariahofstam.Presence_CMS.repositories;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.mariahofstam.Presence_CMS.entity.StatusCode;

public interface StatusCodeRepo extends CrudRepository<StatusCode, Integer> {
}
