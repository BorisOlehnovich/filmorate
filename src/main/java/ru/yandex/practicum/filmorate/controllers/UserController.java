package ru.yandex.practicum.filmorate.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private  final UserService userService;
    @Autowired
    public UserController (UserService userService){
        this.userService = userService;
    }


    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        log.info("Запрос на создание пользоветеля получен: " + user);
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user){
        log.info("Запрос на обновление данных о пользователе получен: " + user);
        return userService.updateUser(user);

    }

    @GetMapping
    public Collection<User> findAll(){
        log.info("Запрос на получение списка всех пользователей получен");
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
        log.info("Запрос на получение пользователя с Id = " + id + " получен");
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public User deleteUserById(@PathVariable int id){
        log.info("Получен запрос на удаление пользователя с ID = " + id);
        return userService.deleteUserById(id);
    }

    @PutMapping("{id}/friends/{friendId}")
    public void addFriend (@PathVariable int id, @PathVariable int friendId){
        log.info("Пользователь с ID = " + id + " добавляет в друзья пользователя с ID = " + friendId);
        userService.addFriends(id, friendId);
    }

    @DeleteMapping("{id}/friends/{friendId}")
    public void deleteFriend (@PathVariable int id, @PathVariable int friendId){
        log.info("Пользователь с ID = " + id + " удаляет из друзей пользователя с ID = " + friendId);
        userService.deleteFriends(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public List<User> getUserFriends (@PathVariable int id){
        log.info("Получен запрос списка всех друзей пользователя с ID = " + id);
        return userService.getUserFriends(id);
    }

    @GetMapping("{id}/friends/common/{friendId}")
    public List<User> getCommonFriends(@PathVariable int id, @PathVariable int friendId){
        log.info("Получен запрос общих друзей пользователя с ID = " + id + " и ID = " + friendId);
        return userService.getCommonFriends(id, friendId);
    }




}
