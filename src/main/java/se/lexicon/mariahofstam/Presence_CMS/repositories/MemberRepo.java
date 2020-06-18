package se.lexicon.mariahofstam.Presence_CMS.repositories;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.mariahofstam.Presence_CMS.entity.Member;

public interface MemberRepo extends CrudRepository<Member, Integer> {
}
