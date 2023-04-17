package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage{
    private Map<Integer, User> users = new HashMap<>();
    private int id = 0;
    private int generateId(){
        return id++;
    }

    @Override
    public User createUser(User user) {
        user.setId(generateId());
        log.debug("Создан пользователь: " + user);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (user.getId() <= 0 || !users.containsKey(user.getId())){
            log.error("Пользователь с таким ID не найден: " + user.getId());
            throw new NotFoundException("Пользователь с таким ID не найден.");
        }


        users.put(user.getId(), user);
        log.debug("Пользователь обновлен = " + user);
        return user;
    }

    @Override
    public Collection<User> findAll() {
        return users.values();
    }

    @Override
    public User getUserById(int id) {
        if (!users.containsKey(id)){
            throw new NotFoundException("Пользователь с указанным ID не найден");
        }
        return users.get(id);
    }

    @Override
    public User deleteUserById(int id) {
        if (!users.containsKey(id)){
            throw new NotFoundException("Пользователь с указанным ID не найден");
        }
        return users.remove(id);
    }
}
