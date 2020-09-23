package se.lexicon.mariahofstam.Presence_CMS.services;

import se.lexicon.mariahofstam.Presence_CMS.dtos.NoteDto;

import java.util.List;
import java.util.NoSuchElementException;

public interface NoteService {
    NoteDto findById(int id);
    List<NoteDto> findAll() throws NoSuchElementException;
    List<NoteDto> findByName(String title) throws NoSuchElementException;

    NoteDto createNote(NoteDto noteDto);
    NoteDto updateNote(NoteDto noteDto);
    boolean deleteNote(int id);
}
