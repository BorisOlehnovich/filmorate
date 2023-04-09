package ru.yandex.practicum.filmorate.exceptions;

public class WrongIdException extends RuntimeException{
    public WrongIdException(String message) {
        super(message);
    }
}
