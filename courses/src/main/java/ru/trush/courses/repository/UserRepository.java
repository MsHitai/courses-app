package ru.trush.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.trush.courses.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
