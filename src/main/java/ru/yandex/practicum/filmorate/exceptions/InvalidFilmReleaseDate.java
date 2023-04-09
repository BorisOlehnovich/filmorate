package ru.yandex.practicum.filmorate.exceptions;

public class InvalidFilmReleaseDate extends RuntimeException{
    public InvalidFilmReleaseDate(String message) {
        super(message);
    }
}
