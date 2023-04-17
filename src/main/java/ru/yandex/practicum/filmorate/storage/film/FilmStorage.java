package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;

public interface FilmStorage {
   Film postNewFilm(Film film);
   Film updateFilm(Film film);
   Collection<Film> findAll();
   Film getFilmById (int id);

   Film deleteFilmById (int id);

}
