package ru.trush.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegistrationDto {

    private Long id;

    @NotEmpty(message = "Имя пользователя не может быть пустым")
    private String username;

    @NotEmpty(message = "Имейл не может быть пустым")
    @Email
    private String email;

    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;

    @NotEmpty(message = "Подтвердите пароль")
    private String confirmPassword;
}
