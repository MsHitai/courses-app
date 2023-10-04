package ru.trush.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.trush.courses.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByTitleLike(String title);

    @Query("from Course c left join fetch c.users left join fetch c.lessons where c.id = :id")
    Optional<Course> findByIdWithAllFields(Long id);
}
