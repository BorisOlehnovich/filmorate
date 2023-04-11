package ru.yandex.practicum.filmorate.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.annotation.CurrentFilmDate;

import java.time.LocalDate;
import java.time.Month;

public class CurrentFilmDateValidator implements ConstraintValidator<CurrentFilmDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate firstFilmDate = LocalDate.of(1895, Month.DECEMBER, 28);
        return firstFilmDate.isBefore(localDate);
    }
}
