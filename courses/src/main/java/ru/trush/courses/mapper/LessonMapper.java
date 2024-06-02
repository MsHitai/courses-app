package ru.trush.courses.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.trush.courses.dto.LessonDto;
import ru.trush.courses.model.Lesson;

@Mapper(componentModel = "spring", uses = CourseMapper.class)
public interface LessonMapper {

    @Mapping(target = "courseId", source = "course.id")
    LessonDto mapToDto(Lesson lesson);

    @Mapping(target = "course.id", source = "courseId")
    Lesson mapToLesson(LessonDto dto);
}
