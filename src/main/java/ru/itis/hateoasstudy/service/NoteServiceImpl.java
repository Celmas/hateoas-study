package ru.itis.hateoasstudy.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.hateoasstudy.model.Note;
import ru.itis.hateoasstudy.model.NoteStatus;
import ru.itis.hateoasstudy.repository.NoteRepository;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    public Note deleteNote(Long id) {
        Note note = noteRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        note.setStatus(NoteStatus.Deleted);
        return noteRepository.save(note);
    }

    public Note postNote(Long id) {
        Note note = noteRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        note.post();
        return noteRepository.save(note);
    }
}
