package ru.trush.courses.mapper;

import lombok.experimental.UtilityClass;
import ru.trush.courses.dto.LessonDto;
import ru.trush.courses.model.Lesson;

@UtilityClass
public class LessonMapper {

    public LessonDto mapToDto(Lesson lesson) {
        return LessonDto.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .text(lesson.getText())
                .courseId(lesson.getCourse().getId())
                .build();
    }

    public Lesson mapToLesson(LessonDto dto) {
        return Lesson.builder()
                .id(dto.getId())
                .text(dto.getText())
                .title(dto.getTitle())
                .build();
    }
}
