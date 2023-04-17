package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {
    private final FilmStorage filmStorage;
    @Autowired
    public FilmService (FilmStorage filmStorage){
        this.filmStorage = filmStorage;
    }

    public Film postNewFilm(Film film){
        return filmStorage.postNewFilm(film);
    }

    public Film updateFilm (Film film){
        return filmStorage.updateFilm(film);
    }

    public Collection<Film> findAll(){
        return filmStorage.findAll();
    }

    public Film getFilmById(int id){
        return filmStorage.getFilmById(id);
    }

    public Film deleteFilmById(int id){
        return filmStorage.deleteFilmById(id);
    }

    public void giveALike(int filmId, int userId){
        Film film = filmStorage.getFilmById(filmId);
        film.getLikes().add(userId);
        filmStorage.updateFilm(film);
    }

    public void deleteLike(int filmId, int userId){
        Film film = filmStorage.getFilmById(filmId);
        film.getLikes().remove(userId);
        filmStorage.updateFilm(film);
    }

    public List<Film> getMostPopularFilms(int count){
        return filmStorage.findAll().stream()
                .sorted((o1, o2) -> o2.getLikes().size() - o1.getLikes().size())
                .limit(count)
                .collect(Collectors.toList());
    }
}
