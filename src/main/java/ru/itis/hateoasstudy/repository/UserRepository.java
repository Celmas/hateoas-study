package ru.itis.hateoasstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoasstudy.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
