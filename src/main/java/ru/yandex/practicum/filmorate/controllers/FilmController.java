package ru.yandex.practicum.filmorate.controllers;

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
    public Film postNewFilm (@RequestBody Film film) {
        log.info("Запрос на публикацию нового фильма получен");
        if (film.getName().isEmpty() || film.getName().isBlank()){
            log.error("Название фильма не может быть пустым");
            throw new EmptyFilmNameException("Название фильма не может быть пустым");
        }
        if (film.getDescription().length() > 200) {
            log.error("Описание фильма не может быть больше 200 символов");
            throw new DescriptionIsVeryLongException("Описание фильма не может быть больше 200 символов");
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, Month.DECEMBER, 28))){
            log.error("Неверно указана дата релиза фильма" +
                    ", в этом году не существовало кинематографа: " + film.getReleaseDate());
            throw new InvalidFilmReleaseDate("Неверно указана дата релиза фильма" +
                    ", в этом году не существовало кинематографа");
        }
        if (film.getDuration().isNegative()){
            log.error("Продолжительность фильма не может быть отрицательной");
            throw new NegativFilmDurationException("Продолжительность фильма не может быть отрицательной");
        }
        film.setId(generateId());
        films.put(film.getId(), film);
        log.debug("Фильм добавлен в библиотеку: " + film);
        return film;
    }

    @PutMapping
    public Film updateFilm (@RequestBody Film film){
        log.info("Запрос на обновление информации о фильме получен");
        if (film.getId() <= 0 || !films.containsKey(film.getId())) {
            log.error("Неверно указан ID фильма");
            throw new WrongIdException("Неверно указан ID фильма");
        }
        if (film.getName().isEmpty() || film.getName().isBlank()){
            log.error("Название фильма не может быть пустым");
            throw new EmptyFilmNameException("Название фильма не может быть пустым");
        }
        if (film.getDescription().length() > 200) {
            log.error("Описание фильма не может быть больше 200 символов");
            throw new DescriptionIsVeryLongException("Описание фильма не может быть больше 200 символов");
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, Month.DECEMBER, 28))){
            log.error("Неверно указана дата релиза фильма" +
                    ", в этом году не существовало кинематографа: " + film.getReleaseDate());
            throw new InvalidFilmReleaseDate("Неверно указана дата релиза фильма" +
                    ", в этом году не существовало кинематографа");
        }
        if (film.getDuration().isNegative()){
            log.error("Продолжительность фильма не может быть отрицательной");
            throw new NegativFilmDurationException("Продолжительность фильма не может быть отрицательной");
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
