package se.lexicon.mariahofstam.Presence_CMS.services;

import se.lexicon.mariahofstam.Presence_CMS.dtos.MemberDto;

import java.util.List;
import java.util.NoSuchElementException;

public interface MemberService {

    MemberDto findById(int id);
    List<MemberDto> findAll() throws NoSuchElementException;
    List<MemberDto> findByFirstName(String firstName) throws NoSuchElementException;
    List<MemberDto> findByLastName (String lastName) throws NoSuchElementException; //Används ej???
    List<MemberDto> findByEmail(String eMail) throws NoSuchElementException;

    MemberDto createMember(MemberDto memberDto);
    MemberDto updateMember(MemberDto memberDto);
    boolean deleteMember(int id);
}
