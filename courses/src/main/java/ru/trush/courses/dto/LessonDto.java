package ru.trush.courses.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class LessonDto {

    private Long id;

    private String title;

    private String text;

    private Long courseId;

    public LessonDto(Long id, String title, String text, Long courseId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.courseId = courseId;
    }

    public LessonDto(Long id, String title, Long courseId) {
        this.id = id;
        this.title = title;
        this.courseId = courseId;
    }
}
