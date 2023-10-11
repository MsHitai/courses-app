package ru.trush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.trush.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
