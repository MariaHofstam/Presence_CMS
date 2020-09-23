package se.lexicon.mariahofstam.Presence_CMS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.mariahofstam.Presence_CMS.converters.EntityDtoConverter;
import se.lexicon.mariahofstam.Presence_CMS.dtos.MemberDto;
import se.lexicon.mariahofstam.Presence_CMS.entity.Member;
import se.lexicon.mariahofstam.Presence_CMS.repositories.MemberRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private MemberRepo repo;
    private EntityDtoConverter converter;

    @Autowired
    public MemberServiceImpl(MemberRepo repo, EntityDtoConverter converter) {
        this.repo = repo;
        this.converter = converter;
    }


    @Override
    public MemberDto findById(int id) {
        Optional<Member> result = repo.findById(id);
        Member member = result.get();
        return converter.memberToDto(member);
    }

    @Override
    public List<MemberDto> findAll() throws NoSuchElementException {
        List<Member> members = (List<Member>) repo.findAll();

        if (members.isEmpty()){
            throw new NoSuchElementException("There are no members in the database.");
        } else{
            return converter.membersToDtos(members);
        }
    }

    @Override
    public List<MemberDto> findByFirstName(String firstName) throws NoSuchElementException {
        List<Member> members = repo.findByFirstNameIgnoreCaseContaining(firstName);

        if (members.isEmpty()){
            throw new NoSuchElementException("There are no member with the firstname - " + firstName + " in the database.");
        } else{
            return converter.membersToDtos(members);
        }
    }

    @Override
    public List<MemberDto> findByLastName(String lastName) throws NoSuchElementException {
        List<Member> members = repo.findByLastNameIgnoreCaseContaining(lastName);

        if (members.isEmpty()){
            throw new NoSuchElementException("There are no member with the lastname - " + lastName + " in the database.");
        } else{
            return converter.membersToDtos(members);
        }
    }

    @Override
    public List<MemberDto> findByEmail(String eMail) throws NoSuchElementException {
        List<Member> members = repo.findByEmailIgnoreCaseContaining(eMail);

        if (members.isEmpty()){
            throw new NoSuchElementException("There are no member with the email - " + eMail + " in the database.");
        } else{
            return converter.membersToDtos(members);
        }
    }

    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Member member = converter.dtoToMember(memberDto);   //Create Member-object from DTO
        repo.save(member);                                  //Save to database
        return converter.memberToDto(member);               //Return dto
    }

    @Override
    public MemberDto updateMember(MemberDto memberDto) {
        Member original = repo.findById(memberDto.getId()).get();
        original.setActive(memberDto.isActive());
        original.setFirstName(memberDto.getFirstName());
        original.setLastName(memberDto.getLastName());
        original.setPhone(memberDto.getPhone());
        original.seteMail(memberDto.geteMail());
        original.setExtraInfo(memberDto.getExtraInfo());
        //What about Group, should it be possible to change group?
        repo.save(original);
        return memberDto;
    }

    @Override
    public boolean deleteMember(int id) {
        repo.deleteById(id);
        return repo.existsById(id);                  // Boolean exist or not?
    }
}
