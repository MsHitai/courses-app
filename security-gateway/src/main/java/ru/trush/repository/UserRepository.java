package ru.trush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.trush.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
