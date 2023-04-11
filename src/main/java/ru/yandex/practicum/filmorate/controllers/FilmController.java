package ru.yandex.practicum.filmorate.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.*;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    private int id=0;
    private Map<Integer, Film> films = new HashMap<>();

    private int generateId(){
        return id++;
    }

    @PostMapping
    public Film postNewFilm (@Valid @RequestBody Film film) {
        log.info("Запрос на публикацию нового фильма получен");
        film.setId(generateId());
        films.put(film.getId(), film);
        log.debug("Фильм добавлен в библиотеку: " + film);
        return film;
    }

    @PutMapping
    public Film updateFilm (@Valid @RequestBody Film film){
        log.info("Запрос на обновление информации о фильме получен");
        if (film.getId() <= 0 || !films.containsKey(film.getId())) {
            log.error("Неверно указан ID фильма");
            throw new ValidException("Неверно указан ID фильма");
        }

        films.put(film.getId(), film);
        log.debug("Информация о фильме обновлена: " + film);
        return film;
    }

    @GetMapping
    public Collection<Film> findAll(){
        return films.values();
    }
}
