package ru.trush.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.trush.courses.dto.LessonDto;
import ru.trush.courses.model.Lesson;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select new ru.trush.courses.dto.LessonDto(l.id, l.title, l.text, l.course.id) " +
            "from Lesson l")
    List<LessonDto> findAllWithProjection();

    @Query("select new ru.trush.courses.dto.LessonDto(l.id, l.title, l.course.id) " +
            "from Lesson l where l.course.id = :id")
    List<LessonDto> findAllForLessonIdWithoutText(@Param("id") long id);
}
