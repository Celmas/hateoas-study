package ru.itis.hateoasstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoasstudy.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
