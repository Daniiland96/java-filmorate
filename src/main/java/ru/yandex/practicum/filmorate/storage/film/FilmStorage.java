package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.Collection;

public interface FilmStorage {

    LocalDate MOVIE_BIRTHDAY = LocalDate.of(1895, 12, 28);

    Collection<Film> findAll();

    Film create(Film film);

    Film update(Film newFilm);

    Film delete(Long id);
}
