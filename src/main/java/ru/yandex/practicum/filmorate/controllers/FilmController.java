package ru.yandex.practicum.filmorate.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController (FilmService filmService){
        this.filmService = filmService;
    }
    @PostMapping
    public Film postNewFilm (@Valid @RequestBody Film film) {
        log.info("Запрос на публикацию нового фильма получен");
        return filmService.postNewFilm(film);
    }

    @PutMapping
    public Film updateFilm (@Valid @RequestBody Film film){
        log.info("Запрос на обновление информации о фильме получен");
        return filmService.updateFilm(film);
    }

    @GetMapping
    public Collection<Film> findAll(){
        log.info("Запрос на получение полного списка фильмов получен");
        return filmService.findAll();
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable int id){
        log.info("Запрос на получение фильма с ID = " + id + " получен");
        return filmService.getFilmById(id);
    }

    @DeleteMapping("/{id}")
    public Film deleteFilmById (@PathVariable int id){
        log.info("Получен запрос на удаление фильма с ID = " + id);
        return filmService.deleteFilmById(id);
    }

    @PutMapping("{id}/like/{userId}")
    public void giveLikeToFilm(@PathVariable int id, @PathVariable int userId){
        log.info("Получен запрос: пользователь с ID = " + userId + "ставит лайк фильму с ID = " + id);
        filmService.giveALike(id, userId);
    }

    @DeleteMapping("{id}/like/{userId}")
    public void deleteLikeOfFilm(@PathVariable int id, @PathVariable int userId){
        log.info("Получен запрос: пользователь с ID = " + userId + "удаляет лайк у фильма с ID = " + id);
        filmService.deleteLike(id, userId);
    }

    @GetMapping("/popular?count={count}")
    public List<Film> getPopularFilms (@RequestParam(name = "count", defaultValue = "10", required = false) int count){
        log.info("Получен запрос списка из " + count + " самых популярных фильма");
        return filmService.getMostPopularFilms(count);
    }




}
