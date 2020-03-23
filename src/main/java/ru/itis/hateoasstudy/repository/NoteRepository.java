package ru.itis.hateoasstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoasstudy.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
