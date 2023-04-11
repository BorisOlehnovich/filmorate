package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import ru.yandex.practicum.filmorate.annotation.CurrentFilmDate;

import java.time.Duration;
import java.time.LocalDate;

@Data
public class Film {
    private int id;
    @NotNull
    @NotBlank(message = "Поле name не может быть пустым")
    private String name;
    @Size(max = 200, message = "Описание не может быть длиннее 200 символов")
    private String description;
    @CurrentFilmDate
    private LocalDate releaseDate;
    @Positive(message = "Продолжительность фильма не может быть отрицательной")
    private int duration;
}
