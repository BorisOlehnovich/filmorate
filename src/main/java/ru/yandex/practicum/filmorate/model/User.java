package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class User {
    private int id;
    @NotBlank
    @Email(message = "Email должен быть введен в правильном формате")
    private String email;
    @NotBlank(message = "Логин не должен быть null или пустым")
    @Pattern(regexp = "^[a-zA-Z0-9]+", message = "Логин не далжен содержать пробелов")
    private String login;
    private String name;
    @Past(message = "Дата рождения не может быть в будущем")
    private LocalDate birthDate;

    private Set<Integer> friendsIds;
}
