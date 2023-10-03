package ru.trush.courses.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseWithUsersDto {
    private Long id;
    @NotBlank
    @NotNull
    private String author;
    @NotBlank
    @NotNull
    private String title;
    private List<LessonDto> lessons;
    private List<UserDto> users;
}