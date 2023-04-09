package ru.yandex.practicum.filmorate.exceptions;

public class DescriptionIsVeryLongException extends RuntimeException{
    public DescriptionIsVeryLongException(String message) {
        super(message);
    }
}
