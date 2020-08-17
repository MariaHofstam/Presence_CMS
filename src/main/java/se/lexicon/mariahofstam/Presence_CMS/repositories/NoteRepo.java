package se.lexicon.mariahofstam.Presence_CMS.repositories;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.mariahofstam.Presence_CMS.entity.Note;

import java.util.List;

public interface NoteRepo extends CrudRepository<Note, Integer> {
    List<Note> findByTitleIgnoreCaseContaining(String name);
}
