package ru.yandex.practicum.filmorate.exceptions;

public class EmptyFilmNameException extends RuntimeException{
    public EmptyFilmNameException(String message) {
        super(message);
    }
}
