package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {
    private int id = 0;
    private Map<Integer, Film> films = new HashMap<>();

    private int generateId(){
        return ++id;
    }

    @Override
    public Film postNewFilm(Film film) {
        film.setId(generateId());
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        if (film.getId() <= 0 || !films.containsKey(film.getId())) {
            log.error("Неверно указан ID фильма");
            throw new NotFoundException("Неверно указан ID фильма");
        }

        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Collection<Film> findAll() {
        return films.values();
    }

    @Override
    public Film getFilmById(int id) {
        if (!films.containsKey(id)){
            throw new NotFoundException("Фильм с указанным ID не найден");
        }
        return films.get(id);
    }

    @Override
    public Film deleteFilmById(int id) {
        if (!films.containsKey(id)) {
            throw new NotFoundException("Фильм с указанным ID не найден");
        }
        return films.remove(id);
    }
}
