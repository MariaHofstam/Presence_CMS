package se.lexicon.mariahofstam.Presence_CMS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.mariahofstam.Presence_CMS.converters.EntityDtoConverter;
import se.lexicon.mariahofstam.Presence_CMS.dtos.NoteDto;
import se.lexicon.mariahofstam.Presence_CMS.entity.Note;
import se.lexicon.mariahofstam.Presence_CMS.repositories.NoteRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private NoteRepo repo;
    private EntityDtoConverter converter;

    @Autowired
    public NoteServiceImpl(NoteRepo repo, EntityDtoConverter converter) {
        this.repo = repo;
        this.converter = converter;
    }

    @Override
    public NoteDto findById(int id) {
        Optional<Note> result = repo.findById(id);
        Note note = result.get();
        return converter.noteToDto(note);
    }

    @Override
    public List<NoteDto> findAll() throws NoSuchElementException {
        List<Note> notes = (List<Note>) repo.findAll();

        if (notes.isEmpty()){
            throw new NoSuchElementException("There are no notes in the database.");
        } else{
            return converter.notesToDtos(notes);
        }
    }

    @Override
    public List<NoteDto> findByName(String title) throws NoSuchElementException {
        List<Note> notes = repo.findByTitleIgnoreCaseContaining(title);

        if (notes.isEmpty()){
            throw new NoSuchElementException("There is no note with the title - " + title + " in the database.");
        } else{
            return converter.notesToDtos(notes);
        }
    }

    @Override
    public NoteDto createNote(NoteDto noteDto) {
        Note note = converter.dtoToNote(noteDto);   //Create Note-object from DTO
        repo.save(note);                            //Save to database
        return converter.noteToDto(note);           //Return dto
    }

    @Override
    public NoteDto updateNote(NoteDto noteDto) {
        Note original = repo.findById(noteDto.getId()).get();
        original.setTitle(noteDto.getTitle());
        original.setText(noteDto.getText());
        repo.save(original);
        return noteDto;
    }

    @Override
    public boolean deleteNote(int id) {
        repo.deleteById(id);
        return repo.existsById(id);                  // Boolean exist or not?
    }
}
