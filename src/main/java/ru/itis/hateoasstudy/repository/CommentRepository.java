package ru.itis.hateoasstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoasstudy.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
