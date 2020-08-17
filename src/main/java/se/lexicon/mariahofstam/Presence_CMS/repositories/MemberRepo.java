package se.lexicon.mariahofstam.Presence_CMS.repositories;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.mariahofstam.Presence_CMS.entity.Member;

import java.util.List;

public interface MemberRepo extends CrudRepository<Member, Integer> {
    List<Member> findByFirstNameIgnoreCaseContaining(String name);
    List<Member> findByLastNameIgnoreCaseContaining(String name);
}
