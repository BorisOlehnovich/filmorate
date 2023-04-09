package ru.yandex.practicum.filmorate.exceptions;

public class InvalidBirthDateException extends RuntimeException{
    public InvalidBirthDateException(String message) {
        super(message);
    }
}
