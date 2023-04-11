package ru.yandex.practicum.filmorate.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidException;
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
    public User createUser(@Valid @RequestBody User user) {
        log.info("Запрос на создание пользоветеля получен");

        user.setId(generateId());
        log.debug("Создан пользователь: " + user);
        users.put(user.getId(), user);
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user){
        log.info("Запрос на обновление данных о пользователе получен");
        if (user.getId() <= 0 || !users.containsKey(user.getId())){
            log.error("Пользователь с таким ID не найден: " + user.getId());
            throw new ValidException("Пользователь с таким ID не найден.");
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
