package ru.yandex.practicum.filmorate.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.yandex.practicum.filmorate.validators.CurrentFilmDateValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CurrentFilmDateValidator.class)
public @interface CurrentFilmDate {
    String message() default "Вы указали дату, когда кинематографа еще не существовало";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    boolean optional() default false;
}
