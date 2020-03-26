package ru.itis.hateoasstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.hateoasstudy.model.Note;

@RepositoryRestResource
public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {
}
