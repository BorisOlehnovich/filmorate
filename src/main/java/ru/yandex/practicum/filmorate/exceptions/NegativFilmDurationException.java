package ru.yandex.practicum.filmorate.exceptions;

public class NegativFilmDurationException extends RuntimeException{
    public NegativFilmDurationException(String message) {
        super(message);
    }
}
