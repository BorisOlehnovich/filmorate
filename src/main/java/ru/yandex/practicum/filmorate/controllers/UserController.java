package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.InvalidBirthDateException;
import ru.yandex.practicum.filmorate.exceptions.InvalidEmailException;
import ru.yandex.practicum.filmorate.exceptions.InvalidLoginException;
import ru.yandex.practicum.filmorate.exceptions.WrongIdException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private Map<Integer, User> users = new HashMap<>();
    private int id = 0;
    private int generateId(){
        return id++;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        log.info("Запрос на создание пользоветеля получен");
        if (user.getEmail() == null || user.getEmail().isBlank() || user.getEmail().contains("@")){
            log.error("Поле Email не должно быть пустым и должно содержать символ @: " + user.getEmail());
            throw new InvalidEmailException("Поле Email не должно быть пустым и должно содержать символ @");
        }
        if (user.getLogin() == null || user.getLogin().isBlank() || user.getLogin().contains(" ")){
            log.error("Поле Login не должно быть пустым и должно содержать символ пробелов: " +user.getLogin());
            throw new InvalidLoginException("Поле Login не должно быть пустым и должно содержать символ пробелов");
        }
        if (user.getBirthday().isAfter(LocalDate.now())){
            log.error("Дата рождения не может быть в будущем: " + user.getBirthday());
            throw new InvalidBirthDateException("Дата рождения не может быть в будущем");
        }
        user.setId(generateId());
        log.debug("Создан пользователь: " + user);
        users.put(user.getId(), user);
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        log.info("Запрос на обновление данных о пользователе получен");
        if (user.getId() <= 0 || !users.containsKey(user.getId())){
            log.error("Пользователь с таким ID не найден: " + user.getId());
            throw new WrongIdException("Пользователь с таким ID не найден.");
        }
        if (user.getEmail() == null || user.getEmail().isBlank() || user.getEmail().contains("@")){
            log.error("Поле Email не должно быть пустым и должно содержать символ @: " + user.getEmail());
            throw new InvalidEmailException("Поле Email не должно быть пустым и должно содержать символ @");
        }
        if (user.getLogin() == null || user.getLogin().isBlank() || user.getLogin().contains(" ")){
            log.error("Поле Login не должно быть пустым и должно содержать символ пробелов: " +user.getLogin());
            throw new InvalidLoginException("Поле Login не должно быть пустым и должно содержать символ пробелов");
        }
        if (user.getBirthday().isAfter(LocalDate.now())){
            log.error("Дата рождения не может быть в будущем: " + user.getBirthday());
            throw new InvalidBirthDateException("Дата рождения не может быть в будущем");
        }

        users.put(user.getId(), user);
        log.debug("Пользователь обновлен = " + user);
        return user;
    }

    @GetMapping
    public Collection<User> findAll(){
        log.info("Запрос на получение списка всех пользователей получен");
        return users.values();
    }
}
